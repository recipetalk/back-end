package com.solution.recipetalk.service.ingredient.userhas;

import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientRegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegisterUserHasIngredientService {
    ResponseEntity<?> addUserHasIngredient(List<UserHasIngredientRegisterDTO> dtos);
}
