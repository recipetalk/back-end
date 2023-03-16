package com.solution.recipetalk.service.user.login.impl;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.dto.user.DuplicateUserDTO;
import com.solution.recipetalk.exception.signup.DuplicatedEmailException;
import com.solution.recipetalk.service.user.login.FindUserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindUserLoginServiceImpl implements FindUserLoginService {
    private final UserLoginRepository userLoginRepository;
    @Override
    public ResponseEntity<?> findDuplicatedEmailInUserLogin(String email) {
        DuplicateUserDTO dto = DuplicateUserDTO.builder().isValid(isDuplicatedEmailExceptionHandler(email)).build();
        return ResponseEntity.ok(dto);
    }

    private Boolean isDuplicatedEmailExceptionHandler(String email) {
        if(!isDuplicatedEmail(email)) {
            throw new DuplicatedEmailException();
        }

        return true;
    }

    private Boolean isDuplicatedEmail(String email) {
        Optional<UserLogin> byEmail = userLoginRepository.findByEmail(email);
        return byEmail.isEmpty();
    }
}
