package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentResponseDTO;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.comment.FindCommentService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindCommentServiceImpl implements FindCommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> findAllParentCommentsOfBoard(Long boardId, Pageable pageable) {

        Page<CommentResponseDTO> responseDTOS = commentRepository.findAllParentCommentByBoard(boardId, pageable, ContextHolder.getUserLoginId());

        return ResponseEntity.ok(responseDTOS);
    }

    @Override
    public ResponseEntity<?> findAllChildCommentsOfBoard(Long boardId, Long parentCommentId, Pageable pageable) {
        Page<CommentResponseDTO> responseDTOS = commentRepository.findAllChildCommentByBoard(boardId, parentCommentId, pageable);

        return ResponseEntity.ok(responseDTOS);
    }

    @Override
    public ResponseEntity<?> findCommentsByUser(Pageable pageable) {
        Long currentLoginUserId = ContextHolder.getUserLoginId();

        UserDetail writer = userDetailRepository.findById(currentLoginUserId).orElseThrow(UserNotFoundException::new);

        Page<CommentResponseDTO> allByWriter = commentRepository.findAllByWriter(writer.getId(), pageable);

        return ResponseEntity.ok(allByWriter);
    }
}
