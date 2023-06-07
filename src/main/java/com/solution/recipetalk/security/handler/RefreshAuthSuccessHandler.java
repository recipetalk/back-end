package com.solution.recipetalk.security.handler;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.security.dto.JWTDTO;
import com.solution.recipetalk.security.dto.properties.JWT;
import com.solution.recipetalk.security.jwt.JWTFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class RefreshAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTFactory jwtFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        JWTDTO jwtdto = JWTDTO.builder()
                .accessToken(jwtFactory.createAccessToken(userLogin))
                .refreshToken(jwtFactory.createRefreshToken(userLogin))
                .build();

        response.setHeader(JWT.ACCESS_TOKEN_HEADER, JWT.TOKEN_PREFIX + jwtdto.getAccessToken());
        response.setHeader(JWT.REFRESH_TOKEN_HEADER, JWT.TOKEN_PREFIX + jwtdto.getRefreshToken());
    }
}
