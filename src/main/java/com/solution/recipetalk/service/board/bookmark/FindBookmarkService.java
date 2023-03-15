package com.solution.recipetalk.service.board.bookmark;

import org.springframework.http.ResponseEntity;

public interface FindBookmarkService {

    ResponseEntity<?> findBookmarkByBoardId(Long boardId);
}
