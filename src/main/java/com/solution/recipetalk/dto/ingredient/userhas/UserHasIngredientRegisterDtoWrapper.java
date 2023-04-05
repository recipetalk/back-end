package com.solution.recipetalk.dto.ingredient.userhas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserHasIngredientRegisterDtoWrapper {
    List<UserHasIngredientRegisterDTO> dtos;
}
