package com.solution.recipetalk.service.verification.token;

import com.solution.recipetalk.domain.verification.token.entity.VerificationSort;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import org.springframework.http.ResponseEntity;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(String email, VerificationSort sort);

    String verifyToken(String token, VerificationSort sort);
}
