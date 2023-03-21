package com.solution.recipetalk.service.fcm;

import com.solution.recipetalk.dto.fcm.FcmTokenRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterFcmTokenService {
    ResponseEntity<?> registerFcmToken(FcmTokenRegisterDTO dto);
}
