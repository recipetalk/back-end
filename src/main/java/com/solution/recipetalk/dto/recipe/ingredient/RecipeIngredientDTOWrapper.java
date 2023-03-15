package com.solution.recipetalk.dto.recipe.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDTOWrapper {
    private List<RecipeIngredientRegisterDTO> recipeIngredientRegisterDTOS;
}
