package com.solution.recipetalk.service.user;

import org.springframework.http.ResponseEntity;

public interface VerifyAuthenticationService {

    ResponseEntity<?> verifyAuthentication(String phoneNum, String authNum);
}
