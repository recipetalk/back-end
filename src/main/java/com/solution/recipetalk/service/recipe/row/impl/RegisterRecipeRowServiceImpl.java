package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTO;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.common.NotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RegisterRecipeRowServiceImpl implements RegisterRecipeRowService {

    private final RecipeRowRepository recipeRowRepository;

    private final RecipeRepository recipeRepository;

    private final ImageRepository imageRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> registerRecipeRow(Long recipeId, RecipeRowRegisterDTO dto) {
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(NotFoundException::new);

        if(!Objects.equals(findRecipe.getBoard().getWriter().getId(), ContextHolder.getUserLoginId())){
            throw new NotAuthorizedException();
        }

        String imgURI = null;
        if(dto.getImg() != null) {
            try {
                imgURI = s3Uploader.upload(dto.getImg(), S3dir.RECIPE_ROW_IMG_DIR);
            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        RecipeRow willSaveData = dto.toRecipeRowEntity(findRecipe, imgURI);

        recipeRowRepository.save(willSaveData);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> registerRecipeRow(Recipe recipe, RecipeRowRegisterDTO dto) {
        String imgURI = null;
        if(dto.getImg() != null) {
            try {
                imgURI = s3Uploader.upload(dto.getImg(), S3dir.RECIPE_ROW_IMG_DIR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        RecipeRow willSaveData = dto.toRecipeRowEntity(recipe, imgURI);

        recipeRowRepository.save(willSaveData);

        return ResponseEntity.ok(null);
    }
}
