package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.dto.user.DuplicateUserDTO;
import com.solution.recipetalk.service.user.FindUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindUserServiceImpl implements FindUserService {

    @Autowired
    private final UserLoginRepository userLoginRepository;


    @Override
    public ResponseEntity<?> findDuplicatedUsernameInUserLogin(String userName) {
        Optional<UserLogin> optionalUserLogin = userLoginRepository.findByUsername(userName);

        DuplicateUserDTO dto = DuplicateUserDTO.builder().isValid(optionalUserLogin.isEmpty()).build();
        return ResponseEntity.ok(dto);
    }
}
