package com.solution.recipetalk.service.board;

import org.springframework.http.ResponseEntity;

public interface RemoveBoardService {
    ResponseEntity<?> hardRemoveByBoardId(Long boardId);
}
