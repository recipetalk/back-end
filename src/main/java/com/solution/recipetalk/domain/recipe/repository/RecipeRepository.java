package com.solution.recipetalk.domain.recipe.repository;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.dto.recipe.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    @Query("SELECT DISTINCT new com.solution.recipetalk.dto.recipe.RecipeDTO(recipe, writer, board, userFollow.id IS NOT NULL, isBoardLiked.id IS NOT NULL, isBookmark.id IS NOT NULL) " +
            "FROM Recipe recipe " +
            "JOIN Board board ON recipe.board = board " +
            "JOIN UserDetail writer ON board.writer = writer " +
            "LEFT JOIN BoardLike isBoardLiked ON isBoardLiked.board = board AND isBoardLiked.user.id = :viewerId " +
            "LEFT JOIN Bookmark  isBookmark ON isBookmark.board = board AND isBoardLiked.user.id = :viewerId " +
            "LEFT JOIN UserFollow userFollow ON userFollow.following.id = writer.id AND userFollow.user.id = :viewerId " +
            "LEFT JOIN UserBlock  userBlocked ON userBlocked.blockedUser.id = writer.id AND userBlocked.user.id = :viewerId " +
            "WHERE writer.isBlocked = FALSE " +
            "AND board.isDeleted = FALSE " +
            "AND userBlocked.id IS NULL " +
            "AND recipe.id = :recipeId " +
            "GROUP BY recipe, writer, board, userFollow.id, isBoardLiked.id, isBookmark.id")
    Optional<RecipeDTO> findRecipeByViewerId(@Param("viewerId")Long viewerId, @Param("recipeId")Long recipeId);
}
