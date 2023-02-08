package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import com.solution.recipetalk.exception.board.CannotFindBoardException;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterCommentServiceImpl implements RegisterCommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> addComment(Long boardId, CommentCreateDTO comment) {
        validateCommentDescription(comment.getDescription());

        Long currentLoginUserId= ContextHolder.getUserLoginId();
        UserDetail writer = userDetailRepository.findById(currentLoginUserId).orElse(null);

        Board board = boardRepository.findById(boardId).orElseThrow(
                CannotFindBoardException::new
        );

        assert writer != null;
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
        * TODO : script는 입력 불가
        */
    }
}
