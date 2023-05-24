package com.solution.recipetalk.domain.ingredient.trimming.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IngredientTrimmingRepository extends JpaRepository<IngredientTrimming, Long>, IngredientTrimmingQueryDslRepository {
    interface IngredientTrimmingResult {
        Long getId();
        String getTitle();
        Long getLikeCount();
        Long getCommentCount();
        String getThumbnailUri();
        String getNickname();
    }

    interface IngredientTrimmingDetailResult {
        IngredientTrimming getIngredientTrimming();
        Board getBoard();
        UserDetail getWriter();

        Boolean getIsLiked();
        Boolean getIsFollowed();

        Boolean getIsBookmarked();

        Ingredient getIngredient();
    }

    @Query("SELECT B.id AS id, B.title AS title, B.likeCount AS likeCount, B.commentCount AS commentCount, IT.thumbnailUri AS thumbnailUri, B.writer.nickname AS nickname " +
            "FROM IngredientTrimming AS IT " +
            "JOIN Board AS B ON B = IT.board " +
            "LEFT JOIN UserBlock AS UB ON UB.user = B.writer AND UB.blockedUser = :currentUser " +
            "WHERE IT.ingredient.id = :ingredientId AND UB.blockedUser IS NULL " +
            "GROUP BY IT.id")
    Optional<Page<IngredientTrimmingResult>> findIngredientTrimmingResultByIdExceptBlockedUser(@Param("ingredientId") Long ingredientId,@Param("currentUser") UserDetail currentUser, Pageable pageable);

    @Query("SELECT IT as ingredientTrimming, B AS board, ud AS writer, br IS NOT NULL AS isLiked, uf IS NOT NULL AS isFollowed, bk IS NOT NULL AS isBookmarked, i as ingredient " +
            "FROM IngredientTrimming AS IT " +
            "JOIN Board AS B ON B = IT.board " +
            "JOIN UserDetail ud ON ud = B.writer " +
            "JOIN Ingredient i ON i.id = IT.ingredient.id " +
            "LEFT JOIN Bookmark bk ON bk.user.id = :viewerId AND B.id = bk.board.id " +
            "LEFT JOIN BoardLike br ON br.user.id = :viewerId AND B.id = br.board.id " +
            "LEFT JOIN UserFollow uf ON uf.user.id = :viewerId AND uf.following.id = ud.id " +
            "LEFT JOIN UserBlock  ub ON ub.user.id = :viewerId AND ub.blockedUser.id = ud.id " +
            "WHERE IT.id = :trimmingId AND ub IS NULL AND B.isDeleted = false AND writer.isBlocked = false AND writer.isDeleted = false")
    Optional<IngredientTrimmingDetailResult> findIngredientTrimmingDetailResultById(@Param("trimmingId") Long trimmingId, @Param("viewerId") Long viewerId);

    @Modifying
    @Query(value = "DELETE FROM ingredient_trimming WHERE board_id = :boardId", nativeQuery = true)
    void hardDeleteByBoardId(@Param("boardId")Long boardId);
}

