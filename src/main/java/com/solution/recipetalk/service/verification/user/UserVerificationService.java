package com.solution.recipetalk.service.verification.user;

public interface UserVerificationService {
    String verifyUser(String username, String token);
}
