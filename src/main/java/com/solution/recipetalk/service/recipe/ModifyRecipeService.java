package com.solution.recipetalk.service.recipe;

import com.solution.recipetalk.dto.recipe.RecipeModifyDTO;
import org.springframework.http.ResponseEntity;

public interface ModifyRecipeService {
    ResponseEntity<?> modifyRecipe(Long recipeId, RecipeModifyDTO dto);
}
