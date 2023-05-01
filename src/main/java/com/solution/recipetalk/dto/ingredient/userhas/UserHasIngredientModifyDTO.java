package com.solution.recipetalk.dto.ingredient.userhas;

import com.solution.recipetalk.domain.ingredient.userhas.entity.IngredientState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class UserHasIngredientModifyDTO {
    private IngredientState state;
    private String quantity;
    private LocalDate expirationDate;
}
