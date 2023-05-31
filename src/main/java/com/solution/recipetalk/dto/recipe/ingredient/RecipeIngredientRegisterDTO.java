package com.solution.recipetalk.dto.recipe.ingredient;


import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecipeIngredientRegisterDTO {
    private Long ingredientId;
    @NonNull
    @Size(max = 10)
    private String quantity;

    @NonNull
    @Size(max = 20)
    private String ingredientName;

    public RecipeIngredient toRecipeIngredientEntity(Ingredient ingredient, Recipe recipe){
        return RecipeIngredient.builder()
                .ingredient(ingredient)
                .quantity(quantity)
                .recipe(recipe)
                .name(ingredientName)
                .build();
    }
}
