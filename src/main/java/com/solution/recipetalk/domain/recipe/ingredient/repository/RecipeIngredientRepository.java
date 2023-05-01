package com.solution.recipetalk.domain.recipe.ingredient.repository;

import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    interface RecipeIngredientList {
        Long getRecipeIngredientId();
        String getIngredientName();
        // TODO: 칼로리
        String getIngredientQuantity();
    }
    List<RecipeIngredient> findRecipeIngredientsByRecipeId(Long recipeId);
}
