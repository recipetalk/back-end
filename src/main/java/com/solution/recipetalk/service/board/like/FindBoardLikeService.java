package com.solution.recipetalk.service.board.like;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindBoardLikeService {

    ResponseEntity<?> findBoardLikeByBoardId(Long boardId);

    ResponseEntity<?> findBoardLikeByUserId(Pageable pageable);
}
