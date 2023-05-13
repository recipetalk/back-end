package com.solution.recipetalk.service.mail;

import org.springframework.http.ResponseEntity;

public interface SendMailForModifyingPasswordService {
    ResponseEntity<?> sendEmail(String username);
}
