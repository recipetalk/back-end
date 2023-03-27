package com.solution.recipetalk.service.mail;

import org.springframework.http.ResponseEntity;

public interface SendMailForTemporaryPasswordService {
    ResponseEntity<?> sendEmail(String username);
}
