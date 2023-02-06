package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.board.CannotFindBoardException;
import com.solution.recipetalk.exception.comment.CommentNotFoundException;
import com.solution.recipetalk.exception.comment.NotAuthorizedToModifyCommentException;
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
        Board board = boardRepository.findById(boardId).orElseThrow(CannotFindBoardException::new);
        Comment comment = commentRepository.findByBoardAndId(board, commentId).orElseThrow(CommentNotFoundException::new);

        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail currentLoginUser = userDetailRepository.findById(userLoginId).orElse(null);

        comment.checkDeletedComment();

        assert currentLoginUser != null;
        validateWhoIsRemovingComment(comment.getWriter(), currentLoginUser);

        commentRepository.deleteById(commentId);

        return ResponseEntity.ok("댓글이 삭제되었습니다");
    }

    @Override
    public ResponseEntity<?> removeAllCommentsOfBoard(Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(CannotFindBoardException::new);
        commentRepository.deleteAllByBoard(board);

        return ResponseEntity.ok("게시물의 댓글이 모두 삭제되었습니다");
    }

    @Override
    public ResponseEntity<?> removeAllComments() {

        commentRepository.deleteAll();

        return ResponseEntity.ok("모든 댓글이 삭제되었습니다");
    }

    private void validateWhoIsRemovingComment(UserDetail writer, UserDetail currentLoginUser) {
        if(currentLoginUser.equals(writer)) {
            throw new NotAuthorizedToModifyCommentException();
        }
    }
}
