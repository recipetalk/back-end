package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.phone.entity.PhoneAuthentication;
import com.solution.recipetalk.domain.user.phone.repository.PhoneAuthenticationRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import com.solution.recipetalk.exception.signup.DuplicatedUserException;
import com.solution.recipetalk.exception.signup.PhoneAuthEntityNotFoundException;
import com.solution.recipetalk.exception.signup.PhoneUnverifiedException;
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
    private final PhoneAuthenticationRepository phoneAuthenticationRepository;

    @Transactional
    public ResponseEntity<?> addUser(SignUpUserReqDto signUpUserReqDto){
        if(userLoginRepository.findByUsername(signUpUserReqDto.getUsername()).isPresent()){
            throw new DuplicatedUserException();
        }

        PhoneAuthentication phoneAuthentication = phoneAuthenticationRepository.findById(signUpUserReqDto.getPhoneNum()).orElseThrow(PhoneAuthEntityNotFoundException::new);

        if(!phoneAuthentication.getIsAuthentication()){
            throw new PhoneUnverifiedException();
        }

        UserDetail userDetail = userDetailRepository.save(signUpUserReqDto.toUserDetail());

        String encryptedPassword = bCryptPasswordEncoder.encode(signUpUserReqDto.getPassword());

        userLoginRepository.save(signUpUserReqDto.toUserLogin(userDetail, encryptedPassword));
        return ResponseEntity.ok(null);
    }
}
