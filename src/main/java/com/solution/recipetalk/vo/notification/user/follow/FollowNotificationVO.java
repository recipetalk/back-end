package com.solution.recipetalk.vo.notification.user.follow;

import com.google.firebase.messaging.*;
import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.vo.notification.NotificationVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowNotificationVO implements NotificationVO {
    private FcmToken fcmTarget;
    private UserDetail user;
    private UserDetail target;
    private static final String FOLLOWING_ADD_MESSAGE_PATTERN = "%s님이 팔로잉하기 시작했습니다.";
    private static final String NOTIFICATION_TITLE = "레시피톡";
    private static final String NAVIGATION = "PROFILE";

    private static final String NAVIGATION_ID_PATTERN = "username=%s";

    @Override
    public Message toMessage() {
        if(fcmTarget == null){
            return null;
        }
        return Message.builder().setNotification(
                        Notification.builder()
                                .setTitle(NOTIFICATION_TITLE)
                                .setBody(String.format(FOLLOWING_ADD_MESSAGE_PATTERN, user.getNickname()))
                                .build()
                ).setToken(fcmTarget.getFcmToken())
                .setApnsConfig(
                        ApnsConfig.builder()
                                .setAps(
                                        Aps.builder()
                                                .setContentAvailable(true)
                                                .build()
                                )//.putHeader("apns-push-type", "background")
                                .putHeader("apns-priority", "5") // 저전력 모드
                                .build()
                ).setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.NORMAL).build())
                .putData("navigation", NAVIGATION)
                .putData("username", user.getUsername())
                .build();
    }

    @Override
    public com.solution.recipetalk.domain.notification.entity.Notification toEntity() {
        return com.solution.recipetalk.domain.notification.entity.Notification.builder()
                .title(NOTIFICATION_TITLE)
                .body(String.format(FOLLOWING_ADD_MESSAGE_PATTERN, user.getNickname()))
                .sort(NotificationSort.FOLLOWING)
                .state(NotificationState.NOT_OPEN)
                .navigationId(String.format(NAVIGATION_ID_PATTERN, user.getUsername()))
                .user(target)
                .build();
    }

    @Override
    public FcmToken getFcmtoken() {
        return fcmTarget;
    }
}
