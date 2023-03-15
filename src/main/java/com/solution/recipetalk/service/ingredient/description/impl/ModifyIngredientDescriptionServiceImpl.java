package com.solution.recipetalk.service.ingredient.description.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.description.repository.IngredientDescriptionRepository;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionModifyDTO;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.ingredient.description.IngredientDescriptionNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.description.ModifyIngredientDescriptionService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyIngredientDescriptionServiceImpl implements ModifyIngredientDescriptionService {
    private final IngredientRepository ingredientRepository;
    private final IngredientDescriptionRepository ingredientDescriptionRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> modifyIngredientDescription(Long ingredientId, IngredientDescriptionModifyDTO dto) {
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        validateCurrentLoginUserIsAdmin(currentUser);

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(IngredientNotFoundException::new);
        IngredientDescription ingredientDescription = ingredientDescriptionRepository.findByIngredient(ingredient).orElseThrow(IngredientDescriptionNotFoundException::new);

        if(dto.getTitle() != null)
            ingredientDescription.getBoard().updateTitle(dto.getTitle());

        if(dto.getDescription() != null)
            ingredientDescription.updateDescription(dto.getDescription());

        if(null != dto.getImgFile()) {
            try {
                String dir = s3Uploader.upload(dto.getImgFile(), S3dir.INGREDIENT_DESCRIPTION_DIR);
                ingredientDescription.updateImgURI(dir);
            } catch(IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        return ResponseEntity.ok(null);
    }

    private void validateCurrentLoginUserIsAdmin(UserDetail currentUser) {
        UserLogin userLogin = userLoginRepository.findByUsername(currentUser.getUsername()).orElseThrow(UserNotFoundException::new);
        if(!userLogin.getRole().equals(RoleType.ADMIN)) {
            throw new NotAuthorizedException();
        }
    }
}
