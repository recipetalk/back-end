package com.solution.recipetalk.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import java.io.IOException;

public class JsonAuthFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {

        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errMsg = "";

        if(exception instanceof UsernameNotFoundException){
            errMsg = "Invalid Username";
        } else if(exception instanceof BadCredentialsException){
            errMsg = "Invalid Password";
        } else {
            exception.printStackTrace();
            errMsg = "Unknown Auth Exception";
        }

        objectMapper.writeValue(res.getWriter(), errMsg);
    }
}
