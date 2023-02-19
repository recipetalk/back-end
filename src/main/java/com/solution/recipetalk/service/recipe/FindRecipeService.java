package com.solution.recipetalk.service.recipe;


import org.springframework.http.ResponseEntity;

public interface FindRecipeService {
    ResponseEntity<?> findRecipeWithId(Long recipeId);
}
