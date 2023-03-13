package com.solution.recipetalk.service.mail;

import org.springframework.http.ResponseEntity;

public interface SendMailService {

    ResponseEntity<?> sendEmail(String toEmail);
}
