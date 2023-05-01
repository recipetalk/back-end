package com.solution.recipetalk.domain.recipe.ingredient.repository;

import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    interface RecipeIngredientResult {
        String getQuantity();
        String getName();
        Boolean getIsHas();
    }

    List<RecipeIngredient> findRecipeIngredientsByRecipeId(Long recipeId);

    @Query(value = "SELECT R.quantity AS quantity, I.name AS name, " +
            "(CASE WHEN UHI.id IS NOT NULL THEN true ELSE false END) AS isHas FROM RecipeIngredient AS R " +
            "JOIN Ingredient AS I ON I.id = R.ingredient.id " +
            "LEFT JOIN UserHasIngredient AS UHI ON UHI.ingredient.id = I.id AND UHI.user.id = :userId " +
            "WHERE R.recipe.id = :recipeId")
    List<RecipeIngredientResult> findRecipeIngredientByUserIdAndRecipeId(Long userId, Long recipeId);
}
