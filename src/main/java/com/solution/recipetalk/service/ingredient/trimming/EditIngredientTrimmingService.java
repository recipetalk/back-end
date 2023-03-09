package com.solution.recipetalk.service.ingredient.trimming;

import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingModifyDTO;
import org.springframework.http.ResponseEntity;

public interface EditIngredientTrimmingService {
    ResponseEntity<?> editIngredientTrimming(IngredientTrimmingModifyDTO dto, Long trimmingId);
}
