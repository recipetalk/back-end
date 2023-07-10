package com.solution.recipetalk.service.comment.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.exception.comment.CommentNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import com.solution.recipetalk.util.ContextHolder;
import com.solution.recipetalk.vo.notification.comment.CommentNotificationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    private final FcmTokenRepository fcmTokenRepository;

    private final ApplicationEventPublisher eventPublisher;

    private UserDetail writer;
    private UserDetail target;
    private Board board;
    private Comment parentComment;
    private Comment newComment;


    @Override
    public ResponseEntity<?> addComment(Long boardId, CommentCreateDTO comment) {
        validateCommentDescription(comment.getDescription());
        addNewComment(boardId, comment);
        sendFcmNotification(newComment, target, writer, board, comment.getParentCommentId());


        return ResponseEntity.ok(null);
    }

    private void addNewComment(Long boardId, CommentCreateDTO comment){

        Long currentLoginUserId = ContextHolder.getUserLoginId();

        writer = userDetailRepository.findById(currentLoginUserId).orElseThrow(UserNotFoundException::new);

        board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        parentComment = comment.getParentCommentId() != null ?
                commentRepository.findById(comment.getParentCommentId()).orElseThrow(CommentNotFoundException::new) : null;

        newComment = comment.toEntity(writer, parentComment, board);

        board.increaseCommentCount();
        commentRepository.save(newComment);
    }

    private void setTarget() {
        target = parentComment != null ? parentComment.getWriter() : board.getWriter();
    }

    private boolean isEqualTargetAndWriter(){
        return target.equals(writer);
    }

    private void sendFcmNotification(Comment comment, UserDetail target, UserDetail writer, Board board, Long parentCommentId){
        setTarget();

        if(isEqualTargetAndWriter()){
            Optional<FcmToken> targetFcm = fcmTokenRepository.findFcmTokenByUser(target);

            CommentNotificationVO commentNotificationVO = CommentNotificationVO.builder()
                    .parentCommentId(parentCommentId)
                    .fcmTarget(targetFcm.orElse(null))
                    .board(board)
                    .comment(comment)
                    .writer(writer)
                    .target(target)
                    .build();
            eventPublisher.publishEvent(commentNotificationVO);
        }
    }

    private void validateCommentDescription(String description) {
        /*
        * TODO : script는 입력 불가
        */
    }
}
