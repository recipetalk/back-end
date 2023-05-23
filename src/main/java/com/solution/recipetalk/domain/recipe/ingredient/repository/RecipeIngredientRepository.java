package com.solution.recipetalk.domain.recipe.ingredient.repository;

import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    interface RecipeIngredientResult {
        String getQuantity();
        String getName();
        Boolean getIsHas();
    }

    List<RecipeIngredient> findRecipeIngredientsByRecipeId(Long recipeId);

    @Query(value = "SELECT ri.name as name, case WHEN uhi is not null THEN true WHEN uhi2 is not null THEN true ELSE false END as isHas, ri.quantity as quantity " +
            "FROM RecipeIngredient ri " +
            "LEFT JOIN Ingredient i ON ri.ingredient = i " +
            "LEFT JOIN UserHasIngredient uhi ON uhi.user.id = :viewerId AND ri.name like '%' + uhi.name + '%' " +
            "LEFT JOIN UserHasIngredient uhi2 ON uhi2.user.id = :viewerId AND ri.ingredient = uhi.ingredient " +
            "WHERE ri.recipe.id = :recipeId")
    List<RecipeIngredientResult> findRecipeIngredientByUserIdAndRecipeId(@Param("viewerId")Long userId, @Param("recipeId")Long recipeId);

}
