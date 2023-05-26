package com.solution.recipetalk.domain.recipe.repository;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.recipe.RecipeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeQueryDslRepository {

    Long countByBoard_Writer(UserDetail writer);

    @Query("SELECT CASE WHEN count(r) > 0 THEN TRUE ELSE FALSE END FROM Recipe r JOIN Board b ON r.board = b WHERE b.writer.id = :writerId AND r.id = :recipeId")
    Boolean existsRecipeByBoardWriter_IdAndId(Long writerId, Long recipeId);

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

//    @Query("SELECT B.id AS boardId, B.title AS title, B.createdDate AS createdDate, (UBL.user.id IS NOT NULL or true) AS isLiked, " +
//            "B.likeCount AS likeCount, B.commentCount AS commentCount, R.thumbnailImgURI AS thumbnailImgURI, R.quantity as quantity, " +
//            "R.id as recipeId, B.boardSort AS boardSort FROM Recipe AS R " +
//            "JOIN Board AS B ON R.board.id = B.id " +
//            "LEFT JOIN BoardLike AS UBL ON UBL.user.id = :userId AND UBL.board.id = B.id " +
//            "WHERE B.writer.id = :userId")
//    Optional<List<RecipeByUsername>> findRecipeByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM recipe WHERE board_id = :recipeId", nativeQuery = true)
    void hardDeleteRecipeById(@Param("recipeId")Long recipeId);
}
