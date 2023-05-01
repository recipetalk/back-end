package com.solution.recipetalk.service.recipe.row;

import org.springframework.http.ResponseEntity;

public interface RemoveRecipeRowService {
    ResponseEntity<?> removeRecipeRow(Long recipeId, Long recipeRowId);
}
