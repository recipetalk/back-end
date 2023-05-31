package com.solution.recipetalk.service.notification.impl;


import com.solution.recipetalk.domain.notification.repository.NotificationRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.notification.NotificationRegisterDTO;
import com.solution.recipetalk.service.notification.RegisterNotificationService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RegisterNotificationServiceImpl implements RegisterNotificationService {

    private final NotificationRepository notificationRepository;
    private final UserDetailRepository userDetailRepository;
    @Override
    public ResponseEntity<?> registerNotification(NotificationRegisterDTO dto) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());

        notificationRepository.save(dto.toEntity(session));

        return ResponseEntity.ok(null);
    }
}
