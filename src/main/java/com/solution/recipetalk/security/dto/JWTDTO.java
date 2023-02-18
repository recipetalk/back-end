package com.solution.recipetalk.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JWTDTO {
    String accessToken;
    String refreshToken;
}
