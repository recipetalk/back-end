package com.solution.recipetalk.domain.recipe.ingredient.group.repository;

import com.solution.recipetalk.domain.recipe.ingredient.group.entity.RecipeIngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientGroupRepository extends JpaRepository<RecipeIngredientGroup, Long> {
}
