package com.solution.recipetalk.domain.notification.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor
@SuperBuilder
public class Notification extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetail user;

    @Enumerated( value = EnumType.STRING)
    @Column(name = "state")
    private NotificationState state;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "navigation_id")
    private String navigationId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sort")
    private NotificationSort sort;
}
