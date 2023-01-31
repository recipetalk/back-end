package com.solution.recipetalk.controller.comment;

import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import com.solution.recipetalk.service.comment.FindCommentService;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {
    @Autowired
    private final RegisterCommentService registerCommentService;

    @Autowired
    private final FindCommentService findCommentService;

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<?> commentAdd(
            @PathVariable Long boardId
            , @Valid @RequestBody CommentCreateDTO request
    ) {
        return registerCommentService.addComment(boardId, request);
    }

    @GetMapping("/{boardId}/comment")
    public ResponseEntity<?> commentListByBoard(@PathVariable Long boardId) {
        return findCommentService.findAllCommentsOfBoard(boardId);
    }

    @GetMapping("/user/comment")
    public ResponseEntity<?> commentListByLoginUser() {
        return findCommentService.findCommentsByUser();
    }
}
