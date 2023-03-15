package com.solution.recipetalk.service.board.bookmark;

import org.springframework.http.ResponseEntity;

public interface ToggleBookmarkService {

    ResponseEntity<?> toggleBookmark(Long boardId);
}
