package com.solution.recipetalk.service.ingredient.userhas;

import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientModifyDTO;
import org.springframework.http.ResponseEntity;

public interface EditUserHasIngredientService {
    ResponseEntity<?> modifyUserHasIngredient(Long userHasIngredientId, UserHasIngredientModifyDTO dto);
}
