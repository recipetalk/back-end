package com.solution.recipetalk.service.fcm.temp.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.dto.fcm.temp.TempFcmTokenRegisterDTO;
import com.solution.recipetalk.service.fcm.temp.RegisterTempFcmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterTempFcmTokenServiceImpl implements RegisterTempFcmTokenService {
    private final TempFcmTokenRepository fcmTokenRepository;

    @Override
    public ResponseEntity<?> registerTempFcmTokenService(TempFcmTokenRegisterDTO dto) {
        Optional<TempFcmToken> tempFcmTokenByEmail = fcmTokenRepository.findTempFcmTokenByEmail(dto.getEmail());
        if(tempFcmTokenByEmail.isPresent()){
            tempFcmTokenByEmail.get().updateFcmToken(dto.getFcmToken());
        }else{
            fcmTokenRepository.save(dto.toEntity());
        }
        return ResponseEntity.ok(null);
    }


}
