package com.solution.recipetalk.domain.recipe.row.repository;

import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRowRepository extends JpaRepository<RecipeRow, Long> {
    List<RecipeRow> findRecipeRowsByRecipeIdOrderById(Long recipeId);

    @Modifying
    @Query("DELETE  FROM RecipeRow rr JOIN Board b ON rr.recipe_id = b.id WHERE ")
    void deleteAllByWriterId(@Param("writerId")Long writerId);
}
