package com.solution.recipetalk.domain.board.like.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.entity.BoardLike;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.board.BoardFacadeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findBoardLikeByBoardAndUser(Board board, UserDetail user);

    @Query(value = "SELECT new com.solution.recipetalk.dto.board.BoardFacadeDTO(b.title, CASE b.boardSort WHEN com.solution.recipetalk.domain.board.entity.BoardSort.RECIPE then recipe.description when com.solution.recipetalk.domain.board.entity.BoardSort.TRIMMING then it.description when com.solution.recipetalk.domain.board.entity.BoardSort.DESCRIPTION then id.description end as description, b.createdDate, true as isLiked, b.commentCount, b.likeCount, b.id, b.boardSort, CASE b.boardSort WHEN com.solution.recipetalk.domain.board.entity.BoardSort.RECIPE then recipe.thumbnailImgURI when com.solution.recipetalk.domain.board.entity.BoardSort.TRIMMING then it.thumbnailUri when com.solution.recipetalk.domain.board.entity.BoardSort.DESCRIPTION then id.imgURI end as thumbnailURI) " +
            "FROM BoardLike bl " +
                "JOIN Board b ON bl.board = b " +
                "LEFT JOIN UserDetail writer ON b.writer = writer " +
                "LEFT JOIN UserBlock ub ON ub.user.id = :viewerId AND writer = ub.blockedUser " +
                "LEFT JOIN Recipe recipe ON recipe.board = b " +
                "LEFT JOIN IngredientTrimming it ON it.board = b " +
                "LEFT JOIN IngredientDescription id ON id.board = b " +
            "WHERE bl.user.id = :viewerId " +
                "AND ub is null " +
                "AND b.isDeleted = false " +
                "AND writer.isBlocked = false " +
                "AND writer.isDeleted = false " +
            "order by bl.id desc ",
        countQuery = "SELECT count(*) FROM BoardLike bl JOIN Board b ON bl.board = b LEFT JOIN UserDetail  writer ON b.writer = writer LEFT JOIN UserBlock ub ON ub.user.id = :viewerId AND writer = ub.blockedUser " +
                "WHERE bl.user.id = :viewerId " +
                "AND ub is null " +
                "AND b.isDeleted = false " +
                "AND writer.isBlocked = false " +
                "AND writer.isDeleted = false ")
    Page<BoardFacadeDTO> findBoardLikeByUserId(@Param("viewerId")Long viewerId, Pageable pageable);
}
