package com.solution.recipetalk.config.jwt;

import com.solution.recipetalk.config.jwt.token.RequestToken;
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


        String jwtAccessTokenHeader = request.getHeader(AccessTokenProperties.HEADER_STRING);
        String jwtRefreshTokenHeader = request.getHeader(RefreshTokenProperties.HEADER_STRING);

//        if (jwtAccessTokenHeader == null || !jwtAccessTokenHeader.startsWith(CommonTokenProperties.TOKEN_PREFIX)){
//            chain.doFilter(request, response);
//            return;
//        }

        RequestToken requestToken = new RequestToken(request);
        try{
            requestToken.getUsername();
            chain.doFilter(request, response);
        }catch (AuthenticationException e){
            restAuthenticationEntryPoint.commence(request, response, e);
        }

    }
}