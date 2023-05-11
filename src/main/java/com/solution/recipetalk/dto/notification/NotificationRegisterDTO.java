package com.solution.recipetalk.dto.notification;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.solution.recipetalk.domain.notification.entity.Notification;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRegisterDTO {
    private String title;
    private String body;
    private NotificationSort notificationSort;
    private Map<String,String> navigations;

    public Notification toEntity(UserDetail userDetail) {
        return Notification.builder()
                .user(userDetail)
                .state(NotificationState.NOT_OPEN)
                .sort(notificationSort)
                .navigationId(Notification.toNavigationId(navigations))
                .title(title)
                .body(body)
                .build();
    }
}
