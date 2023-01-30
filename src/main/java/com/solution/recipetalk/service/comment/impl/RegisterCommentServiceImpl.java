package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentDTO;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

import static com.solution.recipetalk.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class RegisterCommentServiceImpl implements RegisterCommentService {

    private final CommentRepository commentRepository;
    private final UserDetailRepository userDetailRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> addComment(Long boardId, CommentDTO.Create comment) {
        validateCommentDescription(comment.getDescription());

        // TODO: writer와 board validation 로직 필요
        UserDetail writer = userDetailRepository.findById(comment.getWriterId()).orElseThrow(
                () -> new CustomException(NOT_FOUND)
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(NOT_FOUND)
        );

        Comment newComment = Comment.builder()
                .writer(writer)
                .parentComment(
                        // parentComment가 존재한다 = 대댓글
                        // 존재하지 않는다(null 이다) = 그냥 댓글
                        commentRepository.findById(comment.getParentCommentId()).orElse(null)
                )
                .board(board)
                .childComment(new ArrayList<>())
                .description(comment.getDescription())
                .build();

        commentRepository.save(newComment);

        return ResponseEntity.ok(comment);
    }

    private void validateCommentDescription(String description) {
        /*
        * TODO: SQL Injection, XSS 등의 방지 위한 댓글 내용 validation 구현
        */
    }
}
