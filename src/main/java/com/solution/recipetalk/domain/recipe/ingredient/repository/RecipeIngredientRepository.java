package com.solution.recipetalk.domain.recipe.ingredient.repository;

import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    List<RecipeIngredient> findRecipeIngredientsByRecipeId(Long recipeId);
}
