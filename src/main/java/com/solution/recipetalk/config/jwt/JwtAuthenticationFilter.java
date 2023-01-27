package com.solution.recipetalk.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.recipetalk.config.auth.PrincipalDetails;
import com.solution.recipetalk.config.jwt.token.ResponseToken;
import com.solution.recipetalk.config.jwt.token.properties.AccessTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.CommonTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.RefreshTokenProperties;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            ObjectMapper om = new ObjectMapper();
            UserLogin user = om.readValue(request.getInputStream(), UserLogin.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            Authentication authentication = authenticationManager.authenticate(token);


            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        ResponseToken responseToken = new ResponseToken(principalDetails);

        String accessToken = responseToken.getAccessToken();

        response.addHeader(AccessTokenProperties.HEADER_STRING, CommonTokenProperties.TOKEN_PREFIX + accessToken);

    }
}