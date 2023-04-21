package com.solution.recipetalk.service.notification;

import com.solution.recipetalk.dto.notification.NotificationModifyDTO;
import org.springframework.http.ResponseEntity;

public interface ModifyNotificationService {

    ResponseEntity<?> modifyNotificationsOpen(NotificationModifyDTO dto);
}
