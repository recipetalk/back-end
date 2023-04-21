package com.solution.recipetalk.dto.notification;


import com.solution.recipetalk.domain.notification.state.NotificationState;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private Long id;
    private String title;
    private String body;
    private String notificationSort;
    private LocalDateTime createdDate;
    private Map<String,String> navigations;
    private Boolean isOpened;

}
