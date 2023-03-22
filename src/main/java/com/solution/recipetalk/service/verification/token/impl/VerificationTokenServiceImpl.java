package com.solution.recipetalk.service.verification.token.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.domain.verification.token.repository.VerificationTokenRepository;
import com.solution.recipetalk.exception.signup.EmailVerificationFailedException;
import com.solution.recipetalk.exception.signup.PhoneAuthRequestTimeoutException;
import com.solution.recipetalk.service.verification.token.VerificationTokenService;
import com.solution.recipetalk.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TempFcmTokenRepository fcmTokenRepository;

    @Override
    public VerificationToken createVerificationToken(String email) {
        String token = UUIDGenerator.getUUID();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(30);
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .expiryDate(expiryDate)
                .email(email)
                .isVerified(false)
                .build();
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String verifyToken(String token) {
        Optional<VerificationToken> findByToken = verificationTokenRepository.findByToken(token);

        if(findByToken.isEmpty()){
            return "redirect:/auth/verifyFailed";
        }

        VerificationToken tokenData = findByToken.get();

        Optional<TempFcmToken> tempFcmTokenByEmail = fcmTokenRepository.findTempFcmTokenByEmail(tokenData.getEmail());

        if(tokenData.getExpiryDate().isBefore(LocalDateTime.now()) || tempFcmTokenByEmail.isEmpty()){
            return "redirect:/auth/verifyFailed";
        }

        tokenData.ok();





        eventPublisher.publishEvent(tempFcmTokenByEmail.get().getFcmToken());

        return "redirect:/auth/verified";
    }
}
