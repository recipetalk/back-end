package com.solution.recipetalk.dto.ingredient;

import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class IngredientFindResultDTO {
    private String ingredientName;
    private Long ingredientId;




    public static IngredientFindResultDTO toDTO(Ingredient ingredient){
        return IngredientFindResultDTO.builder()
                .ingredientId(ingredient.getId())
                .ingredientName(ingredient.getName())
                .build();
    }
}
