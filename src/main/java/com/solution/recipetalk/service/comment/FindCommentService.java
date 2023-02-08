package com.solution.recipetalk.service.comment;

import org.springframework.http.ResponseEntity;

public interface FindCommentService {
    ResponseEntity<?> findAllCommentsOfBoard(Long boardId);
    ResponseEntity<?> findCommentsByUser();
}
