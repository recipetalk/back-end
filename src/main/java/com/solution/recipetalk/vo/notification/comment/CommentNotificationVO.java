package com.solution.recipetalk.vo.notification.comment;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.vo.notification.NotificationVO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentNotificationVO implements NotificationVO {
    // 누구누구 님이 대댓글을 남겼습니다. {description} <= 클릭시 이동
    //누구누구 님이 댓글을 남겼습니다.
    private Comment comment;
    private UserDetail writer;
    private Long parentCommentId;
    private Board board;
    private FcmToken fcmTarget;
    private UserDetail target;
    private static final String PARENT_COMMENT_ADD_MESSAGE_PATTERN = "%s님이 게시글에 댓글을 남겼습니다. %s";
    private static final String CHILD_COMMENT_ADD_MESSAGE_PATTERN = "%s님이 회원님의 댓글에 답글을 남겼습니다. %s";
    private static final String NOTIFICATION_TITLE = "레시피톡";

    private static final String NAVIGATION_ID_PATTERN = "parentCommentId=%s&boardId=%s&boardSort=%s";

    @Override
    public Message toMessage() {
        if(fcmTarget == null){
            return null;
        }
        if(isChildComment())
            return toChildMessage();
        else{
            return toParentMessage();
        }
    }

    @Override
    public com.solution.recipetalk.domain.notification.entity.Notification toEntity() {
        if(isChildComment()){
            return toChildEntity();
        }else {
            return toParentEntity();
        }
    }

    @Override
    public FcmToken getFcmtoken() {
        return fcmTarget;
    }

    private Boolean isChildComment() {
        return parentCommentId != null && parentCommentId != 0;
    }

    private Message toChildMessage() {
        return Message.builder().setNotification(
                        Notification.builder()
                                .setTitle(NOTIFICATION_TITLE)
                                .setBody(String.format(CHILD_COMMENT_ADD_MESSAGE_PATTERN, writer.getNickname(), comment.getDescription()))
                                .build()
                ).setToken(fcmTarget.getFcmToken())
                .putData("navigation", board.getBoardSort().toString())
                .putData("boardId", String.valueOf(board.getId()))
                .putData("parentCommentId", String.valueOf(parentCommentId))
                .build();
    }

    private Message toParentMessage() {
        return Message.builder().setNotification(
                        Notification.builder()
                                .setTitle(NOTIFICATION_TITLE)
                                .setBody(String.format(PARENT_COMMENT_ADD_MESSAGE_PATTERN, writer.getNickname(), comment.getDescription()))
                                .build()
                ).setToken(fcmTarget.getFcmToken())
                .putData("navigation", board.getBoardSort().toString())
                .putData("boardId", String.valueOf(board.getId()))
                .build();
    }

    private com.solution.recipetalk.domain.notification.entity.Notification toChildEntity() {
        return com.solution.recipetalk.domain.notification.entity.Notification.builder()
                .title(NOTIFICATION_TITLE)
                .body(String.format(CHILD_COMMENT_ADD_MESSAGE_PATTERN, writer.getNickname(), comment.getDescription()))
                .sort(NotificationSort.CHILD_COMMENT)
                .state(NotificationState.NOT_OPEN)
                .navigationId(toNavigationId(parentCommentId, board.getId(),board.getBoardSort().toString()))
                .user(target)
                .build();
    }

    private com.solution.recipetalk.domain.notification.entity.Notification toParentEntity() {
        return com.solution.recipetalk.domain.notification.entity.Notification.builder()
                .title(NOTIFICATION_TITLE)
                .body(String.format(PARENT_COMMENT_ADD_MESSAGE_PATTERN, writer.getNickname(), comment.getDescription()))
                .sort(NotificationSort.CHILD_COMMENT)
                .state(NotificationState.NOT_OPEN)
                .navigationId(toNavigationId(null, board.getId(), board.getBoardSort().toString()))
                .user(target)
                .build();
    }

    private String toNavigationId(Long parentCommentId, Long boardId, String boardSort) {
        return String.format(NAVIGATION_ID_PATTERN,parentCommentId == null ? "null" : parentCommentId, boardId, boardSort);
    }
}
