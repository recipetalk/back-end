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
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/board/{boardId}/comment")
    public ResponseEntity<?> commentParentListByBoard(@PathVariable Long boardId, Pageable pageable) {
        return findCommentService.findAllParentCommentsOfBoard(boardId, pageable);
    }

    @GetMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<?> commentChildListByBoard(@PathVariable(name = "boardId") Long boardId, @PathVariable(name = "commentId") Long parentCommentId, Pageable pageable) {
        return findCommentService.findAllChildCommentsOfBoard(boardId, parentCommentId, pageable);
    }

    @GetMapping("/user/comment")
    public ResponseEntity<?> commentListByLoginUser(Pageable pageable) {
        return findCommentService.findCommentsByUser(pageable);
    }

    @GetMapping("/user/comment/{commentId}")
    public ResponseEntity<?> commentGetById(@PathVariable(name = "commentId") Long commentId) {
        return findCommentService.findCommentById(commentId);
    }

    @PatchMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<?> commentModify(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentModifyDTO commentModifyDTO
    ) {
        return editCommentService.modifyCommentById(boardId, commentId, commentModifyDTO);
    }

    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public ResponseEntity<?> commentRemoveByBoardIdAndCommentId(
            @PathVariable Long boardId,
            @PathVariable Long commentId
    ) {
        return removeCommentService.removeCommentByIdAndBoardId(boardId, commentId);
    }
}
