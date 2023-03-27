package com.solution.recipetalk.service.user.login.password.impl;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.password.TemporaryPassword;
import com.solution.recipetalk.domain.user.login.password.repository.TemporaryPasswordRepository;
import com.solution.recipetalk.service.user.login.password.TemporaryPasswordService;
import com.solution.recipetalk.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TemporaryPasswordServiceImpl implements TemporaryPasswordService {
    private final TemporaryPasswordRepository temporaryPasswordRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String createTemporaryPassword(UserLogin userLogin) {
        if(temporaryPasswordRepository.findByUserLogin(userLogin).isPresent()) {
            temporaryPasswordRepository.deleteById(userLogin);
        }
        String temporaryPassword = UUIDGenerator.getUUID().substring(0, 8);

        String encryptedTempPw = bCryptPasswordEncoder.encode(temporaryPassword);

        temporaryPasswordRepository.save(TemporaryPassword.builder()
                .userLogin(userLogin)
                .temporaryPassword(encryptedTempPw)
                .build());

        return temporaryPassword;
    }
}
