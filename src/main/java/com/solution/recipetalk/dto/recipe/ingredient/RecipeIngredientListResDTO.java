package com.solution.recipetalk.dto.recipe.ingredient;

import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository.RecipeIngredientResult;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class RecipeIngredientListResDTO {

    private String name;
    private Boolean isHas;
    private String quantity;
    private Long ingredientId;


    public static RecipeIngredientListResDTO ToResDTO(RecipeIngredientResult result){
        return RecipeIngredientListResDTO.builder()
                .quantity(result.getQuantity())
                .isHas(result.getIsHas())
                .name(result.getName())
                .ingredientId(result.getIngredientId())
                .build();
    }
}
