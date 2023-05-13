package com.solution.recipetalk.domain.board.bookmark.repository;

import com.solution.recipetalk.domain.board.bookmark.entity.Bookmark;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.board.BoardFacadeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findBookmarkByUserAndBoard(UserDetail user, Board board);

    @Query(value = "SELECT new com.solution.recipetalk.dto.board.BoardFacadeDTO(b.title, CASE b.boardSort WHEN com.solution.recipetalk.domain.board.entity.BoardSort.RECIPE then recipe.description when com.solution.recipetalk.domain.board.entity.BoardSort.TRIMMING then it.description when com.solution.recipetalk.domain.board.entity.BoardSort.DESCRIPTION then id.description end as description, b.createdDate, true as isLiked, b.commentCount, b.likeCount, b.id, b.boardSort, CASE b.boardSort WHEN com.solution.recipetalk.domain.board.entity.BoardSort.RECIPE then recipe.thumbnailImgURI when com.solution.recipetalk.domain.board.entity.BoardSort.TRIMMING then it.thumbnailUri when com.solution.recipetalk.domain.board.entity.BoardSort.DESCRIPTION then id.imgURI end as thumbnailURI, false) " +
            "FROM Bookmark bk " +
            "JOIN Board b ON bk.board = b " +
            "LEFT JOIN UserDetail writer ON b.writer = writer " +
            "LEFT JOIN UserBlock ub ON ub.user.id = :viewerId AND writer = ub.blockedUser " +
            "LEFT JOIN Recipe recipe ON recipe.board = b " +
            "LEFT JOIN IngredientTrimming it ON it.board = b " +
            "LEFT JOIN IngredientDescription id ON id.board = b " +
            "WHERE bk.user.id = :viewerId " +
            "AND ub is null " +
            "AND b.isDeleted = false " +
            "AND writer.isBlocked = false " +
            "AND writer.isDeleted = false " +
            "order by CASE WHEN :sortType = 'NEW' THEN bk.id WHEN :sortType = 'POPULAR' THEN b.likeCount ELSE bk.id END DESC ",
            countQuery = "SELECT count(*) FROM Bookmark bk JOIN Board b ON bk.board = b LEFT JOIN UserDetail  writer ON b.writer = writer LEFT JOIN UserBlock ub ON ub.user.id = :viewerId AND writer = ub.blockedUser " +
                    "WHERE bk.user.id = :viewerId " +
                    "AND ub is null " +
                    "AND b.isDeleted = false " +
                    "AND writer.isBlocked = false " +
                    "AND writer.isDeleted = false ")
    Page<BoardFacadeDTO> findBoardListByUserId(@Param("viewerId")Long viewerId, @Param("sortType")String sortType, Pageable pageable);

    void deleteAllByUser_Id(Long userId);
}
