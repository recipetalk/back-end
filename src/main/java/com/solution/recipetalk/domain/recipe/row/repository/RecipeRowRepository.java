package com.solution.recipetalk.domain.recipe.row.repository;

import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRowRepository extends JpaRepository<RecipeRow, Long> {
    List<RecipeRow> findRecipeRowsByRecipeIdOrderById(Long recipeId);
}
