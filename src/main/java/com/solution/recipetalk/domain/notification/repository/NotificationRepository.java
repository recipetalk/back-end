package com.solution.recipetalk.domain.notification.repository;

import com.solution.recipetalk.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
