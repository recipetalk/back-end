package com.solution.recipetalk.service.fcm;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.http.ResponseEntity;

public interface RemoveFcmTokenService {
    ResponseEntity<?> removeFcmToken();
}
