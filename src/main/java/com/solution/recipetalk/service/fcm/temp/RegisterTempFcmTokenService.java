package com.solution.recipetalk.service.fcm.temp;

import com.solution.recipetalk.dto.fcm.temp.TempFcmTokenRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterTempFcmTokenService {

    ResponseEntity<?> registerTempFcmTokenService(TempFcmTokenRegisterDTO dto);
}
