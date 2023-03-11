package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.exception.comment.CommentNotFoundException;
import com.solution.recipetalk.exception.comment.NotAuthorizedToModifyCommentException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.comment.RemoveCommentService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class RemoveCommentServiceImpl implements RemoveCommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> removeCommentByIdAndBoardId(Long boardId, Long commentId) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        Comment comment = commentRepository.findByBoardAndId(board, commentId).orElseThrow(CommentNotFoundException::new);

        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail currentLoginUser = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);

        comment.checkDeletedComment();

        validateWhoIsRemovingComment(comment.getWriter(), currentLoginUser);

        commentRepository.delete(comment);

        return ResponseEntity.ok(null);
    }

    private void validateWhoIsRemovingComment(UserDetail writer, UserDetail currentLoginUser) {
        if(!currentLoginUser.equals(writer)) {
            throw new NotAuthorizedToModifyCommentException();
        }
    }
}
