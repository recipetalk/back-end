package com.solution.recipetalk.service.comment;

import org.springframework.http.ResponseEntity;

public interface RemoveCommentService {
    ResponseEntity<?> removeCommentByIdAndBoardId(Long boardId, Long commentId);

    ResponseEntity<?> hardRemoveChildCommentsByBoardId(Long boardId);

    ResponseEntity<?> hardRemoveParentCommentsByBoardId(Long boardId);
}
