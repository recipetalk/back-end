package com.solution.recipetalk.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgottenPasswordFindResponseDTO {
    private String username;
    private String email;
    private String fcmToken;
}
