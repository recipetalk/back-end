package com.solution.recipetalk.async.verification;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
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

    @EventListener
    public void handleVerifiedCompleteEvent(String targetToken) throws FirebaseMessagingException {
        firebaseCloudMessageService.sendMessageTo(null, targetToken, "verified", "ok");
    }
}
