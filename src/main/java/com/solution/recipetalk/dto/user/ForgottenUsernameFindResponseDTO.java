package com.solution.recipetalk.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForgottenUsernameFindResponseDTO {
    private String username;
}
