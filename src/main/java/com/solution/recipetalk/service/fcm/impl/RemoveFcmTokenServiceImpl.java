package com.solution.recipetalk.service.fcm.impl;

import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.service.fcm.RemoveFcmTokenService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveFcmTokenServiceImpl implements RemoveFcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;

    @Override
    public ResponseEntity<?> removeFcmToken() {
        fcmTokenRepository.deleteFcmTokenByUser_Id(ContextHolder.getUserLoginId());
        return ResponseEntity.ok(null);
    }
}
