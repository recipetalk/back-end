package com.solution.recipetalk.service.ingredient.description.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.description.repository.IngredientDescriptionRepository;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionRegisterDTO;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.ingredient.description.IngredientDescriptionAlreadyExistsException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.description.RegisterIngredientDescriptionService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterIngredientDescriptionServiceImpl implements RegisterIngredientDescriptionService {
    private final IngredientDescriptionRepository ingredientDescriptionRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserLoginRepository userLoginRepository;
    private final IngredientRepository ingredientRepository;
    private final BoardRepository boardRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> registerIngredientDescription(IngredientDescriptionRegisterDTO dto, Long ingredientId) {
        UserDetail writer = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        validateWriterIsAdmin(writer);

        if(ingredientDescriptionRepository.findByIngredientId(ingredientId).isPresent()) {
            throw new IngredientDescriptionAlreadyExistsException();
        }

        Board createdBoard = dto.toBoardEntity(writer);
        boardRepository.save(createdBoard);

        String dir = "";

        if(null != dto.getImgFile()) {
            try {
                dir = s3Uploader.upload(dto.getImgFile(), S3dir.INGREDIENT_DESCRIPTION_DIR);
            } catch(IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(IngredientNotFoundException::new);
        IngredientDescription ingredientDescription = dto.toIngredientDescription(createdBoard, ingredient, dir, dto.getDescription());

        Long ingredientDescriptionId = ingredientDescriptionRepository.save(ingredientDescription).getId();

        return ResponseEntity.ok(ingredientDescriptionId);
    }

    private void validateWriterIsAdmin(UserDetail writer) {
        UserLogin userLogin = userLoginRepository.findByUsername(writer.getUsername()).orElseThrow(UserNotFoundException::new);
        if(!userLogin.getRole().equals(RoleType.ADMIN)) {
            throw new NotAuthorizedException();
        }
    }
}
