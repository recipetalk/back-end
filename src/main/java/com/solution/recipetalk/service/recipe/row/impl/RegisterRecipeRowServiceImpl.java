package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.img.repository.RecipeRowImgRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTO;
import com.solution.recipetalk.exception.common.NotFoundException;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RegisterRecipeRowServiceImpl implements RegisterRecipeRowService {

    private final RecipeRowRepository recipeRowRepository;

    private final RecipeRepository recipeRepository;

    private final RecipeRowImgRepository recipeRowImgRepository;

    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<?> registerRecipeRow(Long recipeId, List<RecipeRowRegisterDTO> dtos) {
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(NotFoundException::new);

        List<RecipeRow> recipeRows = dtos.stream().map(dto -> dto.toRecipeRowEntity(findRecipe)).toList();

        recipeRowRepository.saveAll(recipeRows);

        //TODO : recipeImg 및 image 저장 로직 필요

        return ResponseEntity.ok(null);
    }
}
