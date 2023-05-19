package com.solution.recipetalk.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDTO {
    private String password;
    private String username;
    private String email;
}
