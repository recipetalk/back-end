package com.solution.recipetalk.config.jwt;

import com.solution.recipetalk.config.jwt.token.RequestToken;
import com.solution.recipetalk.config.jwt.token.ResponseToken;
import com.solution.recipetalk.config.jwt.token.properties.AccessTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.CommonTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.RefreshTokenProperties;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Objects;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private UserLoginRepository userRepository;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserLoginRepository userRepository, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        JwtTokenHeader jwtAccessTokenHeader = new JwtTokenHeader(AccessTokenProperties.HEADER_STRING, request);
        JwtTokenHeader jwtRefreshTokenHeader = new JwtTokenHeader(RefreshTokenProperties.HEADER_STRING, request);

        if (jwtAccessTokenHeader.getHeaderData() == null && jwtRefreshTokenHeader.getHeaderData() == null){
            chain.doFilter(request, response);
            return;
        }

        try{
            RequestToken requestToken;
            if (jwtAccessTokenHeader.getHeaderData() != null){
                requestToken = new RequestToken(jwtAccessTokenHeader);
            }
            else {
                requestToken = new RequestToken(jwtRefreshTokenHeader);
            }
            String username = requestToken.getElementInToken(requestToken.getToken(), "username");
            ResponseToken responseToken = new ResponseToken(username);
            String accessToken = responseToken.makeToken(AccessTokenProperties.HEADER_STRING, AccessTokenProperties.EXPIRE_TIME);
            response.addHeader(AccessTokenProperties.HEADER_STRING, "Bearer " + accessToken);

            if (requestToken.getTokenType().equals(RefreshTokenProperties.HEADER_STRING)){
                String refreshToken = responseToken.makeToken(RefreshTokenProperties.HEADER_STRING, RefreshTokenProperties.EXPIRE_TIME);
                response.addHeader(RefreshTokenProperties.HEADER_STRING, "Bearer " + refreshToken);
            }
            chain.doFilter(request, response);
        }catch (AuthenticationException e){
            restAuthenticationEntryPoint.commence(request, response, e);
        }

    }

}