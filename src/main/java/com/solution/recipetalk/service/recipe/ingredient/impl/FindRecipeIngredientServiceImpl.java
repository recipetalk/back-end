package com.solution.recipetalk.service.recipe.ingredient.impl;

import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientRegisterDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.ingredient.RegisterRecipeIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FindRecipeIngredientServiceImpl {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public ResponseEntity<?> FindRecipeIngredient(Long recipeId) {


        return ResponseEntity.ok(null);
    }
}
