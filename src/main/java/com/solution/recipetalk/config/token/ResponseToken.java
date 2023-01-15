package com.solution.recipetalk.config.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.solution.recipetalk.config.auth.PrincipalDetails;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResponseToken {
    private String accessToken;
    private PrincipalDetails principalDetails;

    public ResponseToken(PrincipalDetails principalDetails){
        this.principalDetails = principalDetails;
        accessToken = makeToken("accessToken", AccessTokenProperties.EXPIRE_TIME);
    }

    public ResponseToken(String name){
        UserLogin user = UserLogin.builder().username(name).build();
        this.principalDetails = new PrincipalDetails(user);
        accessToken = makeToken("accessToken", AccessTokenProperties.EXPIRE_TIME);
    }


    public String makeToken(String tokenName, int expire){
        return JWT.create()
                .withSubject(tokenName)
                .withExpiresAt(new Date(System.currentTimeMillis() + expire ))
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(CommonTokenProperties.SECRET));
    }
}
