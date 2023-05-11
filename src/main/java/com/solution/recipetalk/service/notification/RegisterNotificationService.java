package com.solution.recipetalk.service.notification;

import com.solution.recipetalk.dto.notification.NotificationRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterNotificationService {

    ResponseEntity<?> registerNotification(NotificationRegisterDTO dto);
}
