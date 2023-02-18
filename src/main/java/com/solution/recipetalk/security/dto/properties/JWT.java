package com.solution.recipetalk.security.dto.properties;

public interface JWT {
    String TOKEN_PREFIX = "Bearer ";
    String ACCESS_TOKEN_HEADER = "Authorization";
    String REFRESH_TOKEN_HEADER = "Refresh-token";
}
