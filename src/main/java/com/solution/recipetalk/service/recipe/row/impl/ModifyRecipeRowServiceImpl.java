package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTO;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTOWrapper;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.common.NotAuthorizedToModifyException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.exception.recipe.row.RecipeRowNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.ModifyRecipeRowService;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyRecipeRowServiceImpl implements ModifyRecipeRowService {
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeRepository recipeRepository;
    private final S3Uploader s3Uploader;
    private final RegisterRecipeRowService registerRecipeRowService;
    @Override
    public ResponseEntity<?> modifyRecipeRow(Long recipeId, RecipeRowModifyDTO dto){
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);

        //권한 체킹
        if(recipeRepository.existsRecipeByBoardWriter_IdAndId(ContextHolder.getUserLoginId(), recipeId)){
            throw new NotAuthorizedException();
        }

        RecipeRow findRecipeRow = recipeRowRepository.findRecipeRowByRecipe_IdAndSeqNum(recipeId, dto.getSeqNum()).orElseThrow(RecipeRowNotFoundException::new);

        if ((dto.getIsImgDeleted() || dto.getImg() != null) && findRecipeRow.getImageURI() != null){
           s3Uploader.deleteFile(findRecipeRow.getImageURI(), S3dir.RECIPE_ROW_IMG_DIR);
           findRecipeRow.updateImageURI(null);
        }

        if(dto.getImg() != null) {
            try {
                String uri = s3Uploader.upload(dto.getImg(), S3dir.RECIPE_ROW_IMG_DIR);
                findRecipeRow.updateImageURI(uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        findRecipeRow.changeDescription(dto.getDescription());

        return ResponseEntity.ok(null);
    }
}
