package com.solution.recipetalk.config.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.solution.recipetalk.config.jwt.CustomFilterException;
import com.solution.recipetalk.config.jwt.JwtTokenHeader;
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
    private String token;
    private String tokenType;
    public RequestToken(JwtTokenHeader jwtTokenHeader){
        this.tokenType = jwtTokenHeader.getHeaderType();
        if (!jwtTokenHeader.getHeaderData().startsWith(CommonTokenProperties.TOKEN_PREFIX)){
            throw new CustomFilterException("Not a valid Token (token type : " + tokenType + ")");
        }
        this.token = jwtTokenHeader.getHeaderData().replace(CommonTokenProperties.TOKEN_PREFIX, "");
    }

    public String getElementInToken(String token, String element){
        try{
            return Optional.ofNullable(JWT
                    .require(Algorithm.HMAC512(CommonTokenProperties.SECRET))
                    .build().verify(token)
                    .getClaim(element).asString()).orElseThrow();
        }catch (Exception e){
            throw new CustomFilterException("Token verified failure");
        }
    }
}
