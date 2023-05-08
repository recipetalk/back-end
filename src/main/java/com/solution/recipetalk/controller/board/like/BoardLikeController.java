package com.solution.recipetalk.controller.board.like;

import com.solution.recipetalk.service.board.like.FindBoardLikeService;
import com.solution.recipetalk.service.board.like.ToggleBoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardLikeController {
    private final ToggleBoardLikeService toggleBoardLikeService;
    private final FindBoardLikeService findBoardLikeService;

    @PostMapping("/board/{boardId}/boardLike")
    public ResponseEntity<?> actionBoardLike(@PathVariable(value = "boardId")Long boardId){
        return toggleBoardLikeService.toggleBoardLike(boardId);
    }

    @GetMapping("/board/{boardId}/boardLike")
    public ResponseEntity<?> isBoardLike(@PathVariable(value = "boardId")Long boardId) {
        return findBoardLikeService.findBoardLikeByBoardId(boardId);
    }

    @GetMapping("/user/BoardLike")
    public ResponseEntity<?> boardListByUserId(Pageable pageable){
        return findBoardLikeService.findBoardLikeByUserId(pageable);
    }
}
