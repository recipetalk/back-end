package com.solution.recipetalk.vo.notification.ingredient.userhas;

import com.google.firebase.messaging.*;
import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.domain.notification.entity.Notification;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.vo.notification.NotificationVO;

public class UserHasIngredientNotificationVO implements NotificationVO {
    private String highLayerIngredientName;
    private FcmToken fcmToken;
    private UserDetail userDetail;
    private Long countNum;
    private Long term;

    private static final String TITLE = "레시피톡";

    private static final String BODY_PATTERN_WITHOUT_COUNT = "%s님, %s가 %d일 내에 소비기한 마감됩니다. 얼른 드셔주세요!";
    private static final String BODY_PATTERN = "%s님, %s외 %d개가 %d일 내에 소비기한 마감됩니다. 얼른 드셔주세요!";

    public UserHasIngredientNotificationVO(UserHasIngredientRepository.ExpiryDateImmiIngredientDTO expiryDateImmiIngredient, Long term){
        this.highLayerIngredientName = expiryDateImmiIngredient.getIngredientName();
        this.fcmToken = expiryDateImmiIngredient.getFcmToken();
        this.userDetail = expiryDateImmiIngredient.getUserDetail();
        this.countNum = expiryDateImmiIngredient.getCountNum();
        this.term = term;
    }

    //TODO : 알림이 앱꺼진 상태에서도 오는가? (특히 안드로이드)
    @Override
    public Message toMessage() {
        return Message.builder().setNotification(
                com.google.firebase.messaging.Notification.builder()
                        .setTitle(TITLE)
                        .setBody(notificationBodyBuilder())
                        .build()
                ).setToken(fcmToken.getFcmToken())
                    .setApnsConfig(
                        ApnsConfig.builder()
                            .setAps(
                                Aps.builder()
                                        .setContentAvailable(true)
                                        .build()
                            ).putHeader("apns-push-type", "background")
                                .putHeader("apns-priority", "5")
                                .build()
                ).setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build())
                .build();
    }

    @Override
    public Notification toEntity() {
        return Notification.builder()
                .title(TITLE)
                .body(notificationBodyBuilder())
                .sort(NotificationSort.INGREDIENT)
                .state(NotificationState.NOT_OPEN)
                .navigationId(null)
                .user(userDetail)
                .build();
    }

    @Override
    public FcmToken getFcmtoken() {
        return this.fcmToken;
    }

    public String notificationBodyBuilder() {
        if(countNum > 1){
            return String.format(BODY_PATTERN, userDetail.getNickname(), highLayerIngredientName, countNum-1, term);
        }else {
            return String.format(BODY_PATTERN_WITHOUT_COUNT, userDetail.getNickname(), highLayerIngredientName, term);
        }
    }
}
