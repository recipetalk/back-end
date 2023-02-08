package com.solution.recipetalk.service.board;

import com.solution.recipetalk.dto.board.BoardCreateDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterBoardService {
    ResponseEntity<?> addBoard(BoardCreateDTO boardCreateDTO);
}
