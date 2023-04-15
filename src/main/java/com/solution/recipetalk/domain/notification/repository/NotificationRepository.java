package com.solution.recipetalk.domain.notification.repository;

import com.solution.recipetalk.domain.notification.entity.Notification;
import com.solution.recipetalk.dto.notification.NotificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findNotificationsByUser_Id(Long userId, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Notification n SET n.state = com.solution.recipetalk.domain.notification.state.NotificationState.OPEN WHERE n.id in :ids ")
    void modifyNotificationOpenByIds(List<Long> ids);
}
