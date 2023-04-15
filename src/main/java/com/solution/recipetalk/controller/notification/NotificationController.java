package com.solution.recipetalk.controller.notification;

import com.solution.recipetalk.dto.notification.NotificationModifyDTO;
import com.solution.recipetalk.service.notification.FindNotificationService;
import com.solution.recipetalk.service.notification.ModifyNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {

    private final FindNotificationService findNotificationService;
    private final ModifyNotificationService modifyNotificationService;

    @GetMapping("")
    public ResponseEntity<?> notifications(Pageable pageable) {
        return findNotificationService.findNotifications(pageable);
    }

    @PatchMapping("")
    public ResponseEntity<?> notificationOpens(NotificationModifyDTO dto){
        return modifyNotificationService.modifyNotificationsOpen(dto);
    }
}
