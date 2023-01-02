package com.solution.recipetalk.domain.recipe.repository;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
