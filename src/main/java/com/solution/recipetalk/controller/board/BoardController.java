package com.solution.recipetalk.controller.board;

import com.solution.recipetalk.service.board.FindBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    @Autowired
    private final FindBoardService findBoardService;


    @GetMapping("/board")
    public ResponseEntity<?> boardList() {
        return findBoardService.findAllBoards();
    }

    @GetMapping("/board/search?title={title}")
    public ResponseEntity<?> boardListByTitle(@PathVariable("title") String boardTitle) {
        return findBoardService.findBoardsContainsTitle(boardTitle);
    }

    @GetMapping("/board/search?writer={writer}")
    public ResponseEntity<?> boardListByWriter(@PathVariable("writer") String nickname) {
        return findBoardService.findBoardsByWriter(nickname);
    }

    @GetMapping("/user/board")
    public ResponseEntity<?> boardListByCurrentLoginUser() {
        return findBoardService.findBoardsByCurrentLoginUser();
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> boardDetail(@PathVariable Long boardId) {
        return findBoardService.findBoard(boardId);
    }
}
