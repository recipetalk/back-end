package com.solution.recipetalk.dto.notification;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class NotificationModifyDTO {
    @NonNull
    private List<Long> ids;
}
