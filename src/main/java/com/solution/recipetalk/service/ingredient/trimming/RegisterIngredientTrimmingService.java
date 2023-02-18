package com.solution.recipetalk.service.ingredient.trimming;

import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterIngredientTrimmingService {
    ResponseEntity<?> registerIngredientTrimming(IngredientTrimmingRegisterDTO dto, Long ingredientId);
}
