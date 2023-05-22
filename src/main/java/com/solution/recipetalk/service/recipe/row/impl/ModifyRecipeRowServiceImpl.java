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

        //삭제 후 등록
        recipeRowRepository.deleteByRecipe_IdAndSeqNum(recipeId, dto.getSeqNum());

        registerRecipeRowService.registerRecipeRow(findRecipe, dto.toRegisterDTO());

        return ResponseEntity.ok(null);
    }
}
