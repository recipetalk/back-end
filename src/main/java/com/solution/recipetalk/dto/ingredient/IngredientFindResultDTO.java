package com.solution.recipetalk.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class IngredientFindResultDTO {
    private String ingredientName;
    private Long ingredientId;
}
