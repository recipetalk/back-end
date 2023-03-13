package com.solution.recipetalk.service.ingredient.description;

import org.springframework.http.ResponseEntity;

public interface FindIngredientDescriptionService {
    ResponseEntity<?> findIngredientDescription(Long ingredientId);
}
