package com.solution.recipetalk.controller.notification;

import com.solution.recipetalk.dto.notification.NotificationModifyDTO;
import com.solution.recipetalk.dto.notification.NotificationRegisterDTO;
import com.solution.recipetalk.service.notification.FindNotificationService;
import com.solution.recipetalk.service.notification.ModifyNotificationService;
import com.solution.recipetalk.service.notification.RegisterNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {

    private final FindNotificationService findNotificationService;
    private final ModifyNotificationService modifyNotificationService;

    private final RegisterNotificationService registerNotificationService;

    @GetMapping("")
    public ResponseEntity<?> notifications(Pageable pageable) {
        return findNotificationService.findNotifications(pageable);
    }

    @PatchMapping("")
    public ResponseEntity<?> notificationOpens(NotificationModifyDTO dto){
        return modifyNotificationService.modifyNotificationsOpen(dto);
    }

    @PostMapping("")
    public ResponseEntity<?> notificationAdd(NotificationRegisterDTO dto){
        return registerNotificationService.registerNotification(dto);
    }
}
