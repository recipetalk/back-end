package com.solution.recipetalk.service.recipe;


import com.solution.recipetalk.dto.recipe.RecipeByUserReqDTO;
import com.solution.recipetalk.dto.recipe.RecipeListReqDTO;
import org.springframework.http.ResponseEntity;

public interface FindRecipeService {
    ResponseEntity<?> findRecipeWithId(Long recipeId);
    ResponseEntity<?> findRecipeList(RecipeListReqDTO dto);
    ResponseEntity<?> findPickRecipe();
}
