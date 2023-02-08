package com.solution.recipetalk.controller.comment;

import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import com.solution.recipetalk.dto.comment.CommentModifyDTO;
import com.solution.recipetalk.service.comment.EditCommentService;
import com.solution.recipetalk.service.comment.FindCommentService;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import com.solution.recipetalk.service.comment.RemoveCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private final RegisterCommentService registerCommentService;

    @Autowired
    private final FindCommentService findCommentService;

    @Autowired
    private final EditCommentService editCommentService;

    @Autowired
    private final RemoveCommentService removeCommentService;

    @PostMapping("/board/{boardId}/comment")
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

    @PatchMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<?> commentModify(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @Valid @RequestBody String description
    ) {
        CommentModifyDTO commentModifyDTO = CommentModifyDTO.builder().description(description).build();
        return editCommentService.modifyCommentById(boardId, commentId, commentModifyDTO);
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<?> commentRemoveByBoardIdAndCommentId(
            @PathVariable Long boardId,
            @PathVariable Long commentId
    ) {
        return removeCommentService.removeCommentByIdAndBoardId(boardId, commentId);
    }

    @DeleteMapping("/board/{boardId}/comment")
    public ResponseEntity<?> commentRemoveByBoardId(@PathVariable Long boardId) {
        return removeCommentService.removeAllCommentsOfBoard(boardId);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<?> commentRemove() {
        return removeCommentService.removeAllComments();
    }
}
