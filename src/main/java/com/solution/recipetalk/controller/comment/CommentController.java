package com.solution.recipetalk.controller.comment;

import com.solution.recipetalk.dto.comment.CommentDTO;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{boardId}/comment")
public class CommentController {
    private final RegisterCommentService registerCommentService;

    @PostMapping("/")
    public ResponseEntity<?> commentAdd(
            @PathVariable Long boardId
            , @Valid @RequestBody CommentDTO.Create request
    ) {
        return registerCommentService.addComment(boardId, request);
    }


}
