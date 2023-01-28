package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import com.solution.recipetalk.service.user.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


    @Transactional
    public ResponseEntity<?> addUser(SignUpUserReqDto signUpUserReqDto){
        UserDetail userDetail = userDetailRepository.save(signUpUserReqDto.toUserDetail());
        String encryptedPassword = bCryptPasswordEncoder.encode(signUpUserReqDto.getPassword());
        userLoginRepository.save(signUpUserReqDto.toUserLogin(userDetail, encryptedPassword));
        return ResponseEntity.ok(null);
    }
}
