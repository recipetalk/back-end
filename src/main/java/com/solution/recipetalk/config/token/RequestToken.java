package com.solution.recipetalk.config.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
public class RequestToken {
    private String accessToken;
    public RequestToken(HttpServletRequest request){
        this.accessToken = request.getHeader(AccessTokenProperties.HEADER_STRING).replace(CommonTokenProperties.TOKEN_PREFIX, "");
    }

    public Optional<String> getTokenElement(String token, String element){
        return Optional.ofNullable(JWT
                .require(Algorithm.HMAC512(CommonTokenProperties.SECRET))
                .build().verify(token)
                .getClaim(element).asString());

    }

    public Optional<String> getUsername(){
        return getTokenElement(accessToken, "username");
    }
}
