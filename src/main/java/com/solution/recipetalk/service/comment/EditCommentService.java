package com.solution.recipetalk.service.comment;

import com.solution.recipetalk.dto.comment.CommentModifyDTO;
import org.springframework.http.ResponseEntity;

public interface EditCommentService {
    ResponseEntity<?> modifyCommentById(Long boardId, Long commentId, CommentModifyDTO commentModifyDTO);
}
