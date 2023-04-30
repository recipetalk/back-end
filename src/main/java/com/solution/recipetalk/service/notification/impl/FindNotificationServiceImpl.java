package com.solution.recipetalk.service.notification.impl;

import com.solution.recipetalk.domain.notification.entity.Notification;
import com.solution.recipetalk.domain.notification.repository.NotificationRepository;
import com.solution.recipetalk.dto.notification.NotificationDTO;
import com.solution.recipetalk.service.notification.FindNotificationService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindNotificationServiceImpl implements FindNotificationService {
    private final NotificationRepository notificationRepository;


    @Override
    public ResponseEntity<?> findNotifications(Pageable pageable) {
        Long session = ContextHolder.getUserLoginId();
        Page<Notification> notificationsByUser_id = notificationRepository.findNotificationsByUser_IdOOrderByCreatedDateDesc(session, pageable);
        Page<NotificationDTO> notificationDTOS = notificationsByUser_id.map(Notification::toDTO);
        return ResponseEntity.ok(notificationDTOS);
    }
}
