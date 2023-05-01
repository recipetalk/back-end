package com.solution.recipetalk.dto.ingredient.userhas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class UserHasIngredientModifyDTO {
    private String state;
    private String quantity;
    private LocalDate expirationDate;
}
