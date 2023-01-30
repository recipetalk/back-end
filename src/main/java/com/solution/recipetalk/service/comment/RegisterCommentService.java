package com.solution.recipetalk.service.comment;

import com.solution.recipetalk.dto.comment.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterCommentService {
    ResponseEntity<?> addComment(Long boardId, CommentDTO.Create comment);
}
