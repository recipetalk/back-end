package com.solution.recipetalk.service.board;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindBoardByBoardLikeService {
    ResponseEntity<?> findBoardListByBoardLike(Pageable pageable);
}
