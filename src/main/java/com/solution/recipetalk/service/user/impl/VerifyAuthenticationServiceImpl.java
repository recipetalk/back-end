package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.phone.entity.PhoneAuthentication;
import com.solution.recipetalk.domain.user.phone.repository.PhoneAuthenticationRepository;
import com.solution.recipetalk.exception.signup.PhoneAuthEntityNotFoundException;
import com.solution.recipetalk.exception.signup.PhoneAuthNotEqualException;
import com.solution.recipetalk.service.user.VerifyAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

        phoneAuthenticationData.verifyHandlerWithLastModifiedDate();

        if(!authNum.equals(phoneAuthenticationData.getAuthNum())){
            throw new PhoneAuthNotEqualException();
        }

        phoneAuthenticationData.authComplete();
        return ResponseEntity.ok().build();
    }

    private PhoneAuthentication findByIdExceptionHandler(String phoneNum) {
        return phoneAuthenticationRepository.findById(phoneNum).orElseThrow(PhoneAuthEntityNotFoundException::new);
    }


}
