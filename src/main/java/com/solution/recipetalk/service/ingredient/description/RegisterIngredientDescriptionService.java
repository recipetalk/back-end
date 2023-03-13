package com.solution.recipetalk.service.ingredient.description;

import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterIngredientDescriptionService {
    ResponseEntity<?> registerIngredientDescription(IngredientDescriptionRegisterDTO dto, Long ingredientId);
}
