package com.solution.recipetalk.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBlockRemoveDTO {
    @NonNull
    private String blockedUsername;
}
