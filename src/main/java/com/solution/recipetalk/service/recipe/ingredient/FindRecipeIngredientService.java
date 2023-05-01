package com.solution.recipetalk.service.recipe.ingredient;

import org.springframework.http.ResponseEntity;

public interface FindRecipeIngredientService {
    ResponseEntity<?> findRecipeIngredient(Long recipeId);
}
