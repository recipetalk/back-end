package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentModifyDTO;
import com.solution.recipetalk.dto.comment.CommentResponseDTO;
import com.solution.recipetalk.exception.comment.CommentNotFoundException;
import com.solution.recipetalk.exception.comment.NotAuthorizedToModifyCommentException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.comment.EditCommentService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class EditCommentServiceImpl implements EditCommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> modifyCommentById(Long boardId, Long commentId, CommentModifyDTO commentModifyDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        comment.checkDeletedComment();

        Long currentUserId = ContextHolder.getUserLoginId();
        UserDetail currentLoginUser = userDetailRepository.findById(currentUserId).orElseThrow(UserNotFoundException::new);

        validateWhoIsModifyingComment(comment.getWriter(), currentLoginUser);

        comment.updateDescription(commentModifyDTO.getDescription());
        commentRepository.save(comment);

        return ResponseEntity.ok(CommentResponseDTO.toResponse(comment));
    }

    private void validateWhoIsModifyingComment(UserDetail writer, UserDetail currentLoginUser) {
        if(!currentLoginUser.equals(writer)) {
            // 작성자와 현재 로그인 한 사람이 다르고 관리자가 아니면 예외발생
            throw new NotAuthorizedToModifyCommentException();
        }
    }

    // TODO : 댓글 수정 내용 유효성 검사 필요

}
