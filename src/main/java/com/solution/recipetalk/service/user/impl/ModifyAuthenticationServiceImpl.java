package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.phone.entity.PhoneAuthentication;
import com.solution.recipetalk.domain.user.phone.repository.PhoneAuthenticationRepository;
import com.solution.recipetalk.service.user.ModifyAuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyAuthenticationServiceImpl implements ModifyAuthenticationService {

    private final PhoneAuthenticationRepository phoneAuthenticationRepository;

    @Override
    public void modifyAuthentication(String phoneNum, String authNum) {

        PhoneAuthentication getData = findPhoneAuthIfNullCreatePhoneAuth(phoneNum, authNum);
        getData = phoneAuthenticationRepository.save(getData);

        getData.updateAuthNum(authNum);
        getData.increaseCount();

    }

    private PhoneAuthentication findPhoneAuthIfNullCreatePhoneAuth(String phoneNum, String authNum) {
        Optional<PhoneAuthentication> find = phoneAuthenticationRepository.findById(phoneNum);
        PhoneAuthentication getData = find.orElse(PhoneAuthentication.builder().phoneNum(phoneNum).authNum(authNum).build());

        getData.isValid();

        return getData;
    }
}
