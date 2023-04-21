package com.solution.recipetalk.service.notification;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface FindNotificationService {

    ResponseEntity<?> findNotifications(Pageable pageable);
}
