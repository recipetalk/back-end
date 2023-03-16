package com.solution.recipetalk.domain.ingredient.trimming.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Board getBoard();
    }

    @Query("SELECT B.id AS id, B.title AS title, count(BL.user.id) AS likeCount, count(C.id) AS commentCount, IT.thumbnailUri AS thumbnailUri, B.writer.nickname AS nickname " +
            "FROM IngredientTrimming AS IT " +
            "JOIN Board AS B ON B = IT.board " +
            "LEFT JOIN BoardLike AS BL ON BL.board = B " +
            "LEFT JOIN Comment AS C ON C.board = B " +
            "LEFT JOIN UserBlock AS UB ON UB.user = B.writer AND UB.blockedUser = :currentUser " +
            "WHERE IT.ingredient.id = :ingredientId AND UB.blockedUser IS NULL " +
            "GROUP BY B.id")
    Optional<Page<IngredientTrimmingResult>> findIngredientTrimmingResultByIdExceptBlockedUser(@Param("ingredientId") Long ingredientId,@Param("currentUser") UserDetail currentUser, Pageable pageable);

    @Query("SELECT IT.description AS description, B AS board " +
            "FROM IngredientTrimming AS IT " +
            "JOIN Board AS B ON B = IT.board " +
            "WHERE IT.id = :trimmingId AND IT.ingredient.id = :ingredientId")
    Optional<IngredientTrimmingDetailResult> findIngredientTrimmingDetailResultById(@Param("ingredientId") Long ingredientId, @Param("trimmingId") Long trimmingId);
}

