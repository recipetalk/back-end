package com.solution.recipetalk.service.user.login.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.verification.token.entity.VerificationSort;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.domain.verification.token.repository.VerificationTokenRepository;
import com.solution.recipetalk.dto.user.PasswordResetDTO;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.signup.AuthRequestTimeoutException;
import com.solution.recipetalk.exception.signup.VerifiedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.login.ModifyUserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyUserLoginServiceImpl implements ModifyUserLoginService {
    private final UserLoginRepository userLoginRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final TempFcmTokenRepository tempFcmTokenRepository;

    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public ResponseEntity<?> modifyPassword(PasswordResetDTO dto) {
        VerificationToken verificationToken = verificationTokenRepository.findByEmail(dto.getEmail()).orElseThrow(AuthRequestTimeoutException::new);

        if (!verificationToken.getIsVerified() || verificationToken.getVerificationSort() != VerificationSort.PASSWORD) {
            throw new NotAuthorizedException();
        }

        UserLogin userLogin = userLoginRepository.findByUsername(dto.getUsername()).orElseThrow(UserNotFoundException::new);

        userLogin.updatePassword(passwordEncoder.encode(dto.getPassword()));

        tempFcmTokenRepository.deleteTempFcmTokenByEmail(dto.getEmail());
        verificationTokenRepository.delete(verificationToken);

        return ResponseEntity.ok("성공적으로 변경되었습니다!");
    }
}
