package com.solution.recipetalk.service.fcm;

import com.solution.recipetalk.dto.fcm.FcmTokenDTO;
import org.springframework.http.ResponseEntity;

public interface ModifyFcmTokenService {

    ResponseEntity<?> modifyFcmToken(FcmTokenDTO dto);
}
