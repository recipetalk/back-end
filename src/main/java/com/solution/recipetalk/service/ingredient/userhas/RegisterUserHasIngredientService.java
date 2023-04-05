package com.solution.recipetalk.service.ingredient.userhas;

import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientRegisterDtoWrapper;
import org.springframework.http.ResponseEntity;

public interface RegisterUserHasIngredientService {
    ResponseEntity<?> addUserHasIngredient(UserHasIngredientRegisterDtoWrapper dtos);
}
