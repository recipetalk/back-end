package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.phone.repository.PhoneAuthenticationRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.domain.verification.token.repository.VerificationTokenRepository;
import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import com.solution.recipetalk.exception.signup.DuplicatedUserException;
import com.solution.recipetalk.exception.signup.UnverifiedException;
import com.solution.recipetalk.service.user.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TempFcmTokenRepository tempFcmTokenRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public ResponseEntity<?> addUser(SignUpUserReqDto signUpUserReqDto){
        //아이디 존재
        if(userDetailRepository.findUserDetailByUsername(signUpUserReqDto.getUsername()).isPresent())
            throw new DuplicatedUserException();

        // 인증 시도조차 없다면 에러
        VerificationToken verificationToken = verificationTokenRepository.findByEmail(signUpUserReqDto.getEmail()).orElseThrow(UnverifiedException::new);

        //인증 되지 않은 유저라면
        if(!verificationToken.getIsVerified()){
            throw new UnverifiedException();
        }

        UserDetail userDetail = userDetailRepository.save(signUpUserReqDto.toUserDetail());

        String encryptedPassword = bCryptPasswordEncoder.encode(signUpUserReqDto.getPassword());

        userLoginRepository.save(signUpUserReqDto.toUserLogin(userDetail, encryptedPassword));

        verificationTokenRepository.delete(verificationToken);

        return ResponseEntity.ok(null);
    }
}
