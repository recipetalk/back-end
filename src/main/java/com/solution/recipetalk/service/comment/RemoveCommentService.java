package com.solution.recipetalk.service.comment;

import org.springframework.http.ResponseEntity;

public interface RemoveCommentService {
    ResponseEntity<?> removeCommentByIdAndBoardId(Long boardId, Long commentId);
}
