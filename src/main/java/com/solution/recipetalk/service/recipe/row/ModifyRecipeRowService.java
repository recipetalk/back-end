package com.solution.recipetalk.service.recipe.row;

import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTOWrapper;
import org.springframework.http.ResponseEntity;

public interface ModifyRecipeRowService {
    ResponseEntity<?> modifyRecipeRow(Long recipeId, RecipeRowModifyDTOWrapper wrapper);
}
