package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentInBoardResponseDTO;
import com.solution.recipetalk.dto.comment.CommentResponseDTO;
import com.solution.recipetalk.exception.board.CannotFindBoardException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.comment.FindCommentService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FindCommentServiceImpl implements FindCommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> findAllCommentsOfBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                CannotFindBoardException::new
        );

        List<Comment> allByBoard = commentRepository.findAllByBoard(board);

        List<CommentInBoardResponseDTO> responseDTOS = allByBoard.stream().map(CommentInBoardResponseDTO::toResponse).toList();

        return ResponseEntity.ok(responseDTOS);
    }

    @Override
    public ResponseEntity<?> findCommentsByUser() {
        Long currentLoginUserId = ContextHolder.getUserLoginId();

        UserDetail writer = userDetailRepository.findById(currentLoginUserId).orElseThrow(UserNotFoundException::new);

        List<Comment> allByWriter = commentRepository.findAllByWriter(writer);

        List<CommentResponseDTO> responseDTOS = allByWriter.stream().map(CommentResponseDTO::toResponse).toList();

        return ResponseEntity.ok(responseDTOS);
    }
}
