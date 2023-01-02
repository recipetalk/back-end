package com.solution.recipetalk.domain.recipe.ingredient.repository;

import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
