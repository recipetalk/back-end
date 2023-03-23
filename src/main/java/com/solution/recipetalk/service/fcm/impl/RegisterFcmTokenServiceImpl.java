package com.solution.recipetalk.service.fcm.impl;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.fcm.FcmTokenDTO;
import com.solution.recipetalk.service.fcm.RegisterFcmTokenService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterFcmTokenServiceImpl implements RegisterFcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;
    private final UserDetailRepository userDetailRepository;
    @Override
    public ResponseEntity<?> registerFcmToken(FcmTokenDTO dto) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());

        FcmToken fcmToken;


        Optional<FcmToken> fcmTokenOptional = fcmTokenRepository.findFcmTokenByUser(session);

        if(fcmTokenOptional.isPresent()){
            fcmTokenOptional.get().updateFcmToken(dto.getFcmToken());

            if (dto.getIsListenable()) {
                fcmTokenOptional.get().canListen();
            } else {
                fcmTokenOptional.get().cantListen();
            }

        }else{
            fcmToken = dto.toEntity(session);
            fcmTokenRepository.save(fcmToken);
        }

        return ResponseEntity.ok(null);
    }
}
