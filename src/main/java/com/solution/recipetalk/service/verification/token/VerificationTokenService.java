package com.solution.recipetalk.service.verification.token;

import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.domain.verification.token.type.VerificationType;
import org.springframework.http.ResponseEntity;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(String email, VerificationType type);

    String verifyToken(String token, VerificationType type);
}
