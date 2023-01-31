package com.solution.recipetalk.service.comment;

import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterCommentService {
    ResponseEntity<?> addComment(Long boardId, CommentCreateDTO comment);
}
