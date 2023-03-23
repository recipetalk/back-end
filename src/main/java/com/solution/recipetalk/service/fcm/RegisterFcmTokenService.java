package com.solution.recipetalk.service.fcm;

import com.solution.recipetalk.dto.fcm.FcmTokenDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterFcmTokenService {
    ResponseEntity<?> registerFcmToken(FcmTokenDTO dto);
}

//
