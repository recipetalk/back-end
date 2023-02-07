package com.solution.recipetalk.service.recipe;

import com.solution.recipetalk.dto.recipe.RecipeRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterRecipeService {

    ResponseEntity<?> addRecipe(RecipeRegisterDTO recipeRegisterDTO);
}
