package com.solution.recipetalk.service.ingredient.trimming.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingRegisterDTO;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingRegisterResDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.RegisterIngredientTrimmingService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterIngredientTrimmingServiceImpl implements RegisterIngredientTrimmingService {

    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final IngredientRepository ingredientRepository;
    private final BoardRepository boardRepository;
    private final UserDetailRepository userDetailRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> registerIngredientTrimming(IngredientTrimmingRegisterDTO dto, Long ingredientId) {
        UserDetail writer = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        Ingredient findIngredient = ingredientRepository.findById(ingredientId).orElseThrow(IngredientNotFoundException::new);


        Board createdBoard = dto.toBoardEntity(writer);


        createdBoard = boardRepository.save(createdBoard);
        String dir = "";
        if( null != dto.getThumbnail() ){
            try {
                dir = s3Uploader.upload(dto.getThumbnail(), S3dir.INGREDIENT_TRIMMING_IMG_DIR);
            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        IngredientTrimming createdIngredientTrimming = dto.toIngredientTrimming(createdBoard, findIngredient, dir);
        Long ingredientTrimmingId = ingredientTrimmingRepository.save(createdIngredientTrimming).getId();

        IngredientTrimmingRegisterResDTO createdTrimmingBoard = IngredientTrimmingRegisterResDTO.builder().ingredientTrimmingId(ingredientTrimmingId).build();

        return ResponseEntity.ok(createdTrimmingBoard);
    }

}
