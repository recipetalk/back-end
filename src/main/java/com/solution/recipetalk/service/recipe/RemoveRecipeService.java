package com.solution.recipetalk.service.recipe;

import org.springframework.http.ResponseEntity;

public interface RemoveRecipeService {
    ResponseEntity<?> removeRecipeById(Long recipeId);

    ResponseEntity<?> hardRemoveRecipeById(Long recipeId);
}
