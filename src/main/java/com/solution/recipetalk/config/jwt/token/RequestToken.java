package com.solution.recipetalk.config.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.solution.recipetalk.config.jwt.CustomFilterException;
import com.solution.recipetalk.config.jwt.token.properties.AccessTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.CommonTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.RefreshTokenProperties;
import com.solution.recipetalk.exception.common.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class RequestToken {
    private String accessToken;
    private String refreshToken;
    public RequestToken(HttpServletRequest request){
        this.accessToken = request.getHeader(AccessTokenProperties.HEADER_STRING).replace(CommonTokenProperties.TOKEN_PREFIX, "");
        this.refreshToken = request.getHeader(RefreshTokenProperties.HEADER_STRING).replace(CommonTokenProperties.TOKEN_PREFIX, "");

        System.out.println(this.accessToken);
        System.out.println(this.refreshToken);
    }

    public String getTokenElement(String token, String element){
        try{
            return Optional.ofNullable(JWT
                    .require(Algorithm.HMAC512(CommonTokenProperties.SECRET))
                    .build().verify(token)
                    .getClaim(element).asString()).orElseThrow();
        }catch (Exception e){
            throw new CustomFilterException("Occur Exception");
        }
    }

    public String getUsername(){
        return getTokenElement(accessToken, "username");
    }
}
