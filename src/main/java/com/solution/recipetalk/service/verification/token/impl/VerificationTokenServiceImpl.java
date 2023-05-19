package com.solution.recipetalk.service.verification.token.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.verification.token.entity.VerificationSort;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.domain.verification.token.repository.VerificationTokenRepository;
import com.solution.recipetalk.service.verification.token.VerificationTokenService;
import com.solution.recipetalk.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    public VerificationToken createVerificationToken(String email, VerificationSort sort) {
        String token = UUIDGenerator.getUUID();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(30);
        Optional<VerificationToken> byEmail = verificationTokenRepository.findByEmail(email);
        VerificationToken verificationToken;
        if(byEmail.isEmpty()){
            verificationToken = VerificationToken.builder()
                    .token(token)
                    .expiryDate(expiryDate)
                    .email(email)
                    .isVerified(false)
                    .verificationSort(sort)
                    .build();
        }else{
            verificationToken = byEmail.get();
            verificationToken.updateToken(token);
            verificationToken.updateExpiryDate(expiryDate);
            verificationToken.denied();
            verificationToken.updateSort(sort);
        }

        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String verifyToken(String token, VerificationSort sort) {
        Optional<VerificationToken> findByToken = verificationTokenRepository.findByToken(token);

        if(findByToken.isEmpty()){
            return "redirect:/auth/verifyFailed";
        }

        VerificationToken tokenData = findByToken.get();

        Optional<TempFcmToken> tempFcmTokenByEmail = fcmTokenRepository.findTempFcmTokenByEmail(tokenData.getEmail());

        if(tokenData.getExpiryDate().isBefore(LocalDateTime.now()) || tempFcmTokenByEmail.isEmpty() || tokenData.getVerificationSort() != sort){
            return "redirect:/auth/verifyFailed";
        }

        tokenData.ok();


        eventPublisher.publishEvent(tempFcmTokenByEmail.get());

        return "redirect:/auth/verified";
    }
}
