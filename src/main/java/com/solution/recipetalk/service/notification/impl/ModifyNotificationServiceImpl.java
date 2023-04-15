package com.solution.recipetalk.service.notification.impl;


import com.solution.recipetalk.domain.notification.repository.NotificationRepository;
import com.solution.recipetalk.dto.notification.NotificationModifyDTO;
import com.solution.recipetalk.service.notification.ModifyNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ModifyNotificationServiceImpl implements ModifyNotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<?> modifyNotificationsOpen(NotificationModifyDTO dto) {
        notificationRepository.modifyNotificationOpenByIds(dto.getIds());
        return ResponseEntity.ok(null);
    }
}
