package com.solution.recipetalk.service.recipe.ingredient;

import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientRegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ModifyRecipeIngredientService {
    ResponseEntity<?> modifyRecipeIngredient(Long recipeId, List<RecipeIngredientRegisterDTO> dtos);
}
