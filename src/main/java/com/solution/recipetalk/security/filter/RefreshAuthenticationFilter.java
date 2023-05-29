package com.solution.recipetalk.security.filter;

import com.solution.recipetalk.security.dto.properties.JWT;
import com.solution.recipetalk.security.token.JWTAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class RefreshAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${jwt.refresh-secret-key}") private String refreshKey;
    @Value("${jwt.issuer}") private String issuer;

    public RefreshAuthenticationFilter() {
        super(new AntPathRequestMatcher("/auth/refresh", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String refreshToken = request.getHeader(JWT.REFRESH_TOKEN_HEADER);

        if(refreshToken == null)
            throw new BadCredentialsException("No token.");

        if(!refreshToken.startsWith(JWT.TOKEN_PREFIX)) {
            throw new BadCredentialsException("Not a bearer token.");
        }

        String realToken = refreshToken.substring(JWT.TOKEN_PREFIX.length());

        JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(realToken);
        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }
}
