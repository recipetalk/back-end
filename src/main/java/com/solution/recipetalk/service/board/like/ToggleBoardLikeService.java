package com.solution.recipetalk.service.board.like;

import org.springframework.http.ResponseEntity;

public interface ToggleBoardLikeService {

    ResponseEntity<?> toggleBoardLike(Long boardId);
}
