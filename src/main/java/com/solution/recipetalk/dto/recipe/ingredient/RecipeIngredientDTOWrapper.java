package com.solution.recipetalk.dto.recipe.ingredient;

import jakarta.validation.Valid;
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
    @Valid
    private List<RecipeIngredientRegisterDTO> recipeIngredientRegisterDTOS;
}
