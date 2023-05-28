package com.solution.recipetalk.security.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
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

        if(!checkIfRefreshToken(refreshToken))
            throw new BadCredentialsException("Not a refresh token");

        JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(refreshToken);
        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    private boolean checkIfRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(refreshKey);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).withIssuer(issuer).build();
            verifier.verify(refreshToken);

            return true;
        } catch(Exception e) {
            System.out.println(e.getClass());
            return false;
        }
    }
}
