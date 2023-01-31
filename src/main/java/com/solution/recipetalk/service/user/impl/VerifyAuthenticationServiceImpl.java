package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.phone.PhoneAuthentication;
import com.solution.recipetalk.domain.user.repository.PhoneAuthenticationRepository;
import com.solution.recipetalk.exception.signup.PhoneAuthEntityNotFoundException;
import com.solution.recipetalk.exception.signup.PhoneAuthNotEqualException;
import com.solution.recipetalk.service.user.VerifyAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VerifyAuthenticationServiceImpl implements VerifyAuthenticationService {

    @Autowired
    private final PhoneAuthenticationRepository phoneAuthenticationRepository;

    @Override
    public ResponseEntity<?> verifyAuthentication(String phoneNum, String authNum) {
        PhoneAuthentication phoneAuthenticationData = findByIdExceptionHandler(phoneNum);

        phoneAuthenticationData.isValid();

        if(!authNum.equals(phoneAuthenticationData.getAuthNum())){
            throw new PhoneAuthNotEqualException();
        }

        phoneAuthenticationData.authComplete();
        return ResponseEntity.ok().build();
    }

    private PhoneAuthentication findByIdExceptionHandler(String phoneNum) {
        Optional<PhoneAuthentication> byId = phoneAuthenticationRepository.findById(phoneNum);
        if (byId.isEmpty()){
            throw new PhoneAuthEntityNotFoundException();
        }
        return byId.get();
    }


}
