package com.solution.recipetalk.vo.notification;

import com.google.firebase.messaging.Message;
import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.notification.entity.Notification;

public interface NotificationVO {
    Message toMessage();

    Notification toEntity();

    FcmToken getFcmtoken();
}
