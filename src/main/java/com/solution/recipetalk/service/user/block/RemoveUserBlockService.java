package com.solution.recipetalk.service.user.block;

import com.solution.recipetalk.dto.user.UserBlockRemoveDTO;
import org.springframework.http.ResponseEntity;

public interface RemoveUserBlockService {

    ResponseEntity<?> removeUserBlock(String blockedUsername);
}
