package com.solution.recipetalk.service.fcm;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.http.ResponseEntity;

public interface FirebaseCloudMessageService {
    String sendMessageTo(Long notificationId, String targetToken, String title, String body) throws FirebaseMessagingException;
    String sendMessageTo(Message message) throws FirebaseMessagingException;
}
