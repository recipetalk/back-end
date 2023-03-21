package com.solution.recipetalk.service.fcm;

import org.springframework.http.ResponseEntity;

public interface FirebaseCloudMessageService {
    String sendMessageTo(Long notificationId, String targetToken, String title, String body);
}
