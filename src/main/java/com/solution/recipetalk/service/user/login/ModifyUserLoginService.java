package com.solution.recipetalk.service.user.login;

import com.solution.recipetalk.dto.user.PasswordResetDTO;
import org.springframework.http.ResponseEntity;

public interface ModifyUserLoginService {
    ResponseEntity<?> modifyPassword(PasswordResetDTO dto);
}
