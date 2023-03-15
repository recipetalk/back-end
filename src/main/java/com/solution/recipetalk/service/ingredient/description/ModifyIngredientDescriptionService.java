package com.solution.recipetalk.service.ingredient.description;

import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionModifyDTO;
import org.springframework.http.ResponseEntity;

public interface ModifyIngredientDescriptionService {
    ResponseEntity<?> modifyIngredientDescription(Long ingredientId, IngredientDescriptionModifyDTO dto);
}
