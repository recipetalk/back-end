package com.solution.recipetalk.service.fcm.impl;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.fcm.FcmTokenDTO;
import com.solution.recipetalk.service.fcm.ModifyFcmTokenService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyFcmTokenServiceImpl implements ModifyFcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> modifyFcmToken(FcmTokenDTO dto) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());
        FcmToken findToken = fcmTokenRepository.findFcmTokenByUser(session).orElseThrow();

        findToken.updateFcmToken(dto.getFcmToken());

        if (dto.getIsListenable()) {
            findToken.canListen();
        } else {
            findToken.cantListen();
        }

        return ResponseEntity.ok(null);
    }
}
