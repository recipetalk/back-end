package com.solution.recipetalk.dto.recipe.ingredient;


import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecipeIngredientRegisterDTO {
    private Long ingredientId;
    private Long quantity;

    public RecipeIngredient toRecipeIngredientEntity(Ingredient ingredient, Recipe recipe){
        return RecipeIngredient.builder()
                .ingredient(ingredient)
                .recipe(recipe)
                .build();
    }
}
