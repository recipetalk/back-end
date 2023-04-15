package com.solution.recipetalk.service.notification;

import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface FindNotificationService {

    ResponseEntity<?> findNotifications(Pageable pageable);
}
