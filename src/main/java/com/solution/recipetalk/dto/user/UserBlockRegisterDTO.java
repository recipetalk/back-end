package com.solution.recipetalk.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBlockRegisterDTO {
    @NonNull
    private String blockUsername;
}
