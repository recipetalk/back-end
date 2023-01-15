package com.solution.recipetalk.config.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.solution.recipetalk.config.jwt.token.properties.AccessTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.CommonTokenProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
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
