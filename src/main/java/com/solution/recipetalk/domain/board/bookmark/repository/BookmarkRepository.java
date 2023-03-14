package com.solution.recipetalk.domain.board.bookmark.repository;

import com.solution.recipetalk.domain.board.bookmark.entity.Bookmark;
import com.solution.recipetalk.domain.board.bookmark.id.BookmarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
}
