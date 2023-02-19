package com.solution.recipetalk.service.recipe.row;

import org.springframework.http.ResponseEntity;

public interface FindRecipeRowService {
    ResponseEntity<?> findRecipeRow(Long recipeId);
}
