package com.solution.recipetalk.service.verification.user.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.domain.verification.token.repository.VerificationTokenRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.verification.user.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserVerificationServiceImpl implements UserVerificationService {
    private final UserLoginRepository userLoginRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    private final TempFcmTokenRepository fcmTokenRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public String verifyUser(String username, String token) {
        Optional<VerificationToken> byToken = verificationTokenRepository.findByToken(token);

        if(byToken.isEmpty()) {
            return "redirect:/auth/verifyFailed";
        }

        VerificationToken tokenData = byToken.get();

        Optional<TempFcmToken> tempFcmTokenByEmail = fcmTokenRepository.findTempFcmTokenByEmail(tokenData.getEmail());


        if(tokenData.getExpiryDate().isBefore(LocalDateTime.now()) || tempFcmTokenByEmail.isEmpty() || tokenData.getIsVerified()) {
            return "redirect:/auth/verifyFailed";
        }

        if(userLoginRepository.findByUsername(username).isEmpty()) {
            throw new UserNotFoundException();
        }

        tokenData.ok();

        eventPublisher.publishEvent(tempFcmTokenByEmail.get());

        return "redirect:/auth/verified";
    }
}
