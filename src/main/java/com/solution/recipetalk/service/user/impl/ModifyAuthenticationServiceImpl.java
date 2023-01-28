package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.phone.PhoneAuthentication;
import com.solution.recipetalk.domain.user.repository.PhoneAuthenticationRepository;
import com.solution.recipetalk.service.user.ModifyAuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyAuthenticationServiceImpl implements ModifyAuthenticationService {

    private final PhoneAuthenticationRepository phoneAuthenticationRepository;

    @Override
    public void modifyAuthentication(String phoneNum, String authNum) {
        Optional<PhoneAuthentication> find = findPhoneAuthenticationIfNotNull(phoneNum);
        PhoneAuthentication getData = find.orElse(PhoneAuthentication.builder().phoneNum(phoneNum).authNum(authNum).build());

        getData.updateAuthNum(authNum);
        getData.increaseCount();

        phoneAuthenticationRepository.save(getData);
    }

    private Optional<PhoneAuthentication> findPhoneAuthenticationIfNotNull(String phoneNum) {
        return phoneAuthenticationRepository.findById(phoneNum);
    }
}
