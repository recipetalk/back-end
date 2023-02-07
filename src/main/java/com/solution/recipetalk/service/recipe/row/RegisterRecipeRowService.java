package com.solution.recipetalk.service.recipe.row;

import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegisterRecipeRowService {
    ResponseEntity<?> registerRecipeRow(Long recipeId, List<RecipeRowRegisterDTO> dtos);
}
