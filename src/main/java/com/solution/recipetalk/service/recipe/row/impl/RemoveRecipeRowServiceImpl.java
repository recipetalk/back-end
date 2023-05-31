package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;

import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.exception.common.NotAuthorizedToRemoveException;
import com.solution.recipetalk.exception.recipe.row.RecipeRowNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.RemoveRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveRecipeRowServiceImpl implements RemoveRecipeRowService {
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeRepository recipeRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> removeRecipeRow(Long recipeId, Long seqNum){
        Long loginUserId = ContextHolder.getUserLoginId();

        if(!recipeRepository.existsRecipeByBoardWriter_IdAndId(ContextHolder.getUserLoginId(), recipeId)){
            throw new NotAuthorizedToRemoveException();
        }

        RecipeRow findRecipeRow = recipeRowRepository.findRecipeRowByRecipe_IdAndSeqNum(recipeId, seqNum).orElseThrow(RecipeRowNotFoundException::new);

        // S3 이미지 삭제
        if(findRecipeRow.getImageURI() != null)
            s3Uploader.deleteFile(findRecipeRow.getImageURI(), S3dir.RECIPE_ROW_IMG_DIR);

        // recipe row, recipe row img, image 삭제
        recipeRowRepository.delete(findRecipeRow);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> removeRecipeRowNoWriterChecking(Long recipeId, Long seqNum) {
        Long loginUserId = ContextHolder.getUserLoginId();

        RecipeRow findRecipeRow = recipeRowRepository.findRecipeRowByRecipe_IdAndSeqNum(recipeId, seqNum).orElseThrow(RecipeRowNotFoundException::new);

        // S3 이미지 삭제
        if(findRecipeRow.getImageURI() != null)
            s3Uploader.deleteFile(findRecipeRow.getImageURI(), S3dir.RECIPE_ROW_IMG_DIR);

        // recipe row, recipe row img, image 삭제
        recipeRowRepository.delete(findRecipeRow);

        return ResponseEntity.ok(null);
    }

    @Override
    public void removeRecipeRowNoWriterCheckingById(Long id) {
        RecipeRow findRecipeRow = recipeRowRepository.findById(id).orElseThrow(RecipeRowNotFoundException::new);
        if(findRecipeRow.getImageURI() != null)
            s3Uploader.deleteFile(findRecipeRow.getImageURI(), S3dir.RECIPE_ROW_IMG_DIR);

        // recipe row, recipe row img, image 삭제
        recipeRowRepository.delete(findRecipeRow);
    }
}
