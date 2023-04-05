package com.solution.recipetalk.service.ingredient.userhas;

import org.springframework.http.ResponseEntity;

public interface RemoveUserHasIngredientService {
    ResponseEntity<?> removeUserHasIngredient(Long userHasIngredientId);
}
