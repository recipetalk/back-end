package com.solution.recipetalk.service.recipe.ingredient.impl;

import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository.RecipeIngredientResult;
import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientListResDTO;
import com.solution.recipetalk.service.recipe.ingredient.FindRecipeIngredientService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FindRecipeIngredientServiceImpl implements FindRecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;

    public ResponseEntity<?> findRecipeIngredient(Long recipeId) {
        Long loginUserId = ContextHolder.getUserLoginId();
        List<RecipeIngredientResult> recipeIngredientResults = recipeIngredientRepository.findRecipeIngredientByUserIdAndRecipeId(loginUserId, recipeId);


        return ResponseEntity.ok(recipeIngredientResults.stream().map(RecipeIngredientListResDTO::ToResDTO));
    }
}
