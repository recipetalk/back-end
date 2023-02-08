package com.solution.recipetalk.service.recipe.ingredient;

import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientRegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegisterRecipeIngredientService {
    ResponseEntity<?> registerRecipeIngredient(Long recipeId, List<RecipeIngredientRegisterDTO> dtos);
}
