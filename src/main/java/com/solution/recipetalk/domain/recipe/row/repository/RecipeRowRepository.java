package com.solution.recipetalk.domain.recipe.row.repository;

import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRowRepository extends JpaRepository<RecipeRow, Long> {
    List<RecipeRow> findRecipeRowsByRecipeIdOrderById(Long recipeId);

    @Modifying
    @Query("DELETE FROM RecipeRow WHERE recipe.id = :recipeId and seqNum = :seqNum")
    void deleteByRecipe_IdAndSeqNum(@Param("recipeId") Long recipeId, @Param("seqNum") Long seqNum);

    Optional<RecipeRow> findRecipeRowByRecipe_IdAndSeqNum(Long recipeId, Long seqNum);

    @Query("SELECT RecipeRow FROM RecipeRow WHERE recipe.id = :recipeId AND seqNum > :seqNum ")
    List<RecipeRow> findRecipeRowsByRecipeIdAndSeqNum(@Param("recipeId")Long recipeId, @Param("seqNum")Long seqNum);
}
