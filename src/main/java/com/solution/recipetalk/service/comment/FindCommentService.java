package com.solution.recipetalk.service.comment;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindCommentService {
    ResponseEntity<?> findAllParentCommentsOfBoard(Long boardId, Pageable pageable);

    ResponseEntity<?> findAllChildCommentsOfBoard(Long boardId, Long parentCommentId, Pageable pageable);
    ResponseEntity<?> findCommentsByUser(Pageable pageable);
}
