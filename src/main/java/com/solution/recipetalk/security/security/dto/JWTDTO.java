package com.solution.recipetalk.security.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JWTDTO {
    String accessToken;
    String refreshToken;
}
