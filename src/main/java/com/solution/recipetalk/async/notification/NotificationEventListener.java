package com.solution.recipetalk.async.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.service.fcm.FirebaseCloudMessageService;
import com.solution.recipetalk.vo.notification.NotificationVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Async("Notification")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NotificationEventListener {

    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final TempFcmTokenRepository tempFcmTokenRepository;
    private final FcmTokenRepository fcmTokenRepository;

    @EventListener
    public void handleVerifiedCompleteEvent(final TempFcmToken fcmToken) {
        try {
            if( !fcmToken.isValid()) {
                throw new RuntimeException("don't used FcmToken");
            }
            System.out.println("fcmToken: " + fcmToken);
            firebaseCloudMessageService.sendMessageTo(null, fcmToken.getFcmToken(), "레시피톡 이메일 인증", "이메일 인증이 완료되었습니다.");
        } catch (RuntimeException | FirebaseMessagingException e) {
            tempFcmTokenRepository.delete(fcmToken);
        }
    }

    @EventListener
    public void handleNotificationEvent(final NotificationVO notificationVO){
        try {
            firebaseCloudMessageService.sendMessageTo(notificationVO.toMessage());
        } catch (RuntimeException | FirebaseMessagingException e) {
            fcmTokenRepository.delete(notificationVO.getFcmtoken());
        }
    }
}
