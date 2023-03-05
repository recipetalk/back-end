package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.recipe.RecipeRegisterDTO;
import com.solution.recipetalk.dto.recipe.RecipeRegisterResDTO;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.RegisterRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterRecipeServiceImpl implements RegisterRecipeService {

    private final BoardRepository boardRepository;
    private final RecipeRepository recipeRepository;
    private final UserDetailRepository userDetailRepository;
    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> addRecipe(RecipeRegisterDTO recipeRegisterDTO) {
        Long writerId = ContextHolder.getUserLoginId();
        UserDetail writer = userDetailRepository.getReferenceById(writerId);

        Board newBoard = recipeRegisterDTO.toBoardEntity(writer);

        boardRepository.save(newBoard);

        String thumbnailURI = "";

        if (null != recipeRegisterDTO.getThumbnail()){
            try {
                thumbnailURI = s3Uploader.upload(recipeRegisterDTO.getThumbnail(), S3dir.RECIPE_IMG_DIR);
            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        Recipe newRecipe = recipeRegisterDTO.toRecipeEntity(thumbnailURI, newBoard);
        newRecipe = recipeRepository.save(newRecipe);


        return ResponseEntity.ok(
                RecipeRegisterResDTO.builder()
                        .recipeId(newRecipe.getId())
                        .build()
        );
    }
}
