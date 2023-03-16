package com.solution.recipetalk.domain.ingredient.trimming.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientTrimmingRepository extends JpaRepository<IngredientTrimming, Long> {
    interface IngredientTrimmingResult {
        Long getId();
        String getTitle();
        Long getLikeCount();
        Long getCommentCount();
        String getThumbnailUri();
        String getNickname();
    }

    interface IngredientTrimmingDetailResult {
        String getDescription();
    }

    @Query("SELECT B.id AS id, B.title AS title, count(BL.id) AS likeCount, count(C.id) AS commentCount, IT.thumbnailUri AS thumbnailUri, B.writer.nickname AS nickname " +
            "FROM IngredientTrimming AS IT " +
            "JOIN Board AS B ON B = IT.board " +
            "LEFT JOIN BoardLike AS BL ON BL.board = B " +
            "LEFT JOIN Comment AS C ON C.board = B " +
            "WHERE IT.ingredient.id = :ingredientId " +
            "GROUP BY B.id")
    Optional<List<IngredientTrimmingResult>> findIngredientTrimmingResultById(@Param("ingredientId") Long ingredientId);

    @Query("SELECT IT.description AS description " +
            "FROM IngredientTrimming AS IT " +
            "WHERE IT.id = :trimmingId AND IT.ingredient.id = :ingredientId")
    Optional<IngredientTrimmingDetailResult> findIngredientTrimmingDetailResultById(@Param("ingredientId") Long ingredientId, @Param("trimmingId") Long trimmingId);
}

