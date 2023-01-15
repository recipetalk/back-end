package com.solution.recipetalk.config.jwt;

import com.solution.recipetalk.config.jwt.token.RequestToken;
import com.solution.recipetalk.config.jwt.token.properties.AccessTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.CommonTokenProperties;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private UserLoginRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserLoginRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        String jwtHeader = request.getHeader(AccessTokenProperties.HEADER_STRING);

        if (jwtHeader == null || !jwtHeader.startsWith(CommonTokenProperties.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        RequestToken requestToken = new RequestToken(request);
        chain.doFilter(request, response);

    }
}