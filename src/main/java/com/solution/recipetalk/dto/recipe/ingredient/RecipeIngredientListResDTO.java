package com.solution.recipetalk.dto.recipe.ingredient;

import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository.RecipeIngredientResult;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class RecipeIngredientListResDTO implements RecipeIngredientResult {

    private String name;
    private Boolean isHas;
    private String quantity;


    public static RecipeIngredientListResDTO ToResDTO(RecipeIngredientResult result){
        return RecipeIngredientListResDTO.builder()
                .quantity(result.getQuantity())
                .isHas(result.getIsHas())
                .name(result.getName())
                .build();
    }

    @Override
    public String getQuantity() {
        return quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean getIsHas() {
        return isHas;
    }
}
