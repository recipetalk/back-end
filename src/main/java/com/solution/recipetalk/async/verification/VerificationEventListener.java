package com.solution.recipetalk.async.verification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.service.fcm.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Async("Notification")
@Transactional
@RequiredArgsConstructor
public class VerificationEventListener {

    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final FcmTokenRepository fcmTokenRepository;

    @EventListener
    public void handleVerifiedCompleteEvent(final FcmToken fcmToken) {
        try {
            if( !fcmToken.isValid()) {
                throw new RuntimeException("don't used FcmToken");
            }

            firebaseCloudMessageService.sendMessageTo(null, fcmToken.getFcmToken(), "verified", "ok");
        } catch (RuntimeException | FirebaseMessagingException e) {
            fcmTokenRepository.delete(fcmToken);
            throw new RuntimeException(e);
        }
    }
}
