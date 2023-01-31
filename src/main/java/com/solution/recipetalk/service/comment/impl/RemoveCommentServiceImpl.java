package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.RoleType;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.exception.board.CannotFindBoardException;
import com.solution.recipetalk.exception.comment.NoCommentFoundException;
import com.solution.recipetalk.exception.comment.NotAdminUserException;
import com.solution.recipetalk.exception.comment.NotAuthorizedToModifyComment;
import com.solution.recipetalk.service.comment.RemoveCommentService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RemoveCommentServiceImpl implements RemoveCommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> removeCommentByIdAndBoardId(Long boardId, Long commentId) {
        Board board = boardRepository.findById(boardId).orElseThrow(CannotFindBoardException::new);
        Comment comment = commentRepository.findByBoardAndId(board, commentId).orElseThrow(NoCommentFoundException::new);

        validateWhoIsRemovingComment(comment.getWriter());

        comment.softDeleteComment(true);
        commentRepository.save(comment);

        return ResponseEntity.ok("댓글이 삭제되었습니다");
    }

    @Override
    public ResponseEntity<?> removeAllCommentsOfBoard(Long boardId) {
        validateIsAdmin();

        Board board = boardRepository.findById(boardId).orElseThrow(CannotFindBoardException::new);
        List<Comment> commentsByBoard = commentRepository.findAllByBoard(board);

        for(Comment comment : commentsByBoard) {
            comment.softDeleteComment(true);
            commentRepository.save(comment);
        }

        return ResponseEntity.ok("게시물의 댓글이 모두 삭제되었습니다");
    }

    @Override
    public ResponseEntity<?> removeAllComments() {
        validateIsAdmin();

        List<Comment> allComments = commentRepository.findAll();

        for(Comment comment : allComments) {
            comment.softDeleteComment(true);
            commentRepository.save(comment);
        }

        return ResponseEntity.ok("모든 댓글이 삭제되었습니다");
    }

    private void validateWhoIsRemovingComment(UserDetail writer) {
        UserDetail currentlyLoginUser = ContextHolder.getUserDetail();
        if(currentlyLoginUser.equals(writer) && !currentlyLoginUser.getRole().equals(RoleType.ADMIN)) {
            throw new NotAuthorizedToModifyComment();
        }
    }

    private void validateIsAdmin() {
        UserDetail currentlyLoginUser = ContextHolder.getUserDetail();
        if(!currentlyLoginUser.getRole().equals(RoleType.ADMIN)) {
            throw new NotAdminUserException();
        }
    }
}