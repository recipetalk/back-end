package com.solution.recipetalk.service.user.login.impl;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
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

    @Override
    public ResponseEntity<?> modifyPassword(String username, String password) {
        UserLogin userLogin = userLoginRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        userLogin.updatePassword(passwordEncoder.encode(password));

        return ResponseEntity.ok("성공적으로 변경되었습니다!");
    }
}
