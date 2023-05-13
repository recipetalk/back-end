package com.solution.recipetalk.domain.notification.repository;

import com.solution.recipetalk.domain.notification.entity.Notification;
import com.solution.recipetalk.dto.notification.NotificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findNotificationsByUser_IdOrderByIdDesc(Long userId, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Notification n SET n.state = com.solution.recipetalk.domain.notification.state.NotificationState.OPEN WHERE n.id in :ids ")
    void modifyNotificationOpenByIds(List<Long> ids);

    void deleteAllByUser_Id(Long userId);
}
