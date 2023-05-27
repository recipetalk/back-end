package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.dto.recipe.RecipeModifyDTO;
import com.solution.recipetalk.exception.common.NotAuthorizedToModifyException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.ModifyRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyRecipeServiceImpl implements ModifyRecipeService {
    private final RecipeRepository recipeRepository;

    private final S3Uploader s3Uploader;

    public ResponseEntity<?> modifyRecipe(Long recipeId, RecipeModifyDTO dto){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        Board board = recipe.getBoard();
        Long recipeWriterId = board.getWriter().getId();
        Long loginUserId = ContextHolder.getUserLoginId();

        if (!Objects.equals(recipeWriterId, loginUserId)){
            throw new NotAuthorizedToModifyException();
        }

        board.changeTitle(dto.getTitle());
        String thumbnailURI = null;
        if(recipe.getThumbnailImgURI() != null && dto.getIsThumbnailDeleted() ){
            s3Uploader.deleteFile(recipe.getThumbnailImgURI(), S3dir.RECIPE_IMG_DIR);
        }
        if(dto.getThumbnailImg() != null) {
            try {
                thumbnailURI = s3Uploader.upload(dto.getThumbnailImg(), S3dir.RECIPE_IMG_DIR);

            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        recipe.changeByRecipeModifyDTO(dto, thumbnailURI);

        return ResponseEntity.ok(null);
    }
}
