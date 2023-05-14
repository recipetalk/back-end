package com.solution.recipetalk.service.verification.user.impl;

import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.verification.token.repository.VerificationTokenRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.verification.user.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserVerificationServiceImpl implements UserVerificationService {
    private final UserLoginRepository userLoginRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public String verifyUser(String username, String token) {
        if(verificationTokenRepository.findByToken(token).isEmpty()) {
            return "redirect:/auth/verifyFailed";
        }

        if(userLoginRepository.findByUsername(username).isEmpty()) {
            throw new UserNotFoundException();
        }

        return "redirect:/auth/reset/pw?user=" + username;
    }
}
