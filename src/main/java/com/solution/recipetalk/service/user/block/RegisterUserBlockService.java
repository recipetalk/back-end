package com.solution.recipetalk.service.user.block;

import com.solution.recipetalk.dto.user.UserBlockRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterUserBlockService {

    ResponseEntity<?> addUserBlock(UserBlockRegisterDTO dto);
}
