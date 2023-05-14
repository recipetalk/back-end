package com.solution.recipetalk.service.mail;

import com.solution.recipetalk.domain.verification.token.type.VerificationType;
import org.springframework.http.ResponseEntity;

public interface SendMailService {

    ResponseEntity<?> sendEmail(String toEmail, VerificationType type);
}
