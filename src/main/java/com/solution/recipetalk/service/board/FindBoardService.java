package com.solution.recipetalk.service.board;
import org.springframework.http.ResponseEntity;

public interface FindBoardService {
    ResponseEntity<?> findAllBoards();

    ResponseEntity<?> findBoardsContainsTitle(String boardTitle);

    ResponseEntity<?> findBoardsByWriter(String nickname);

    ResponseEntity<?> findBoardsByCurrentLoginUser();

    ResponseEntity<?> findBoard(Long boardId);
}
