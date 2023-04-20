package com.solution.recipetalk.domain.board.bookmark.repository;

import com.solution.recipetalk.domain.board.bookmark.entity.Bookmark;
import com.solution.recipetalk.domain.board.bookmark.id.BookmarkId;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findBookmarkByUserAndBoard(UserDetail user, Board board);
}
