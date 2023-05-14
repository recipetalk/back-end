package com.solution.recipetalk.service.user.login;

import org.springframework.http.ResponseEntity;

public interface ModifyUserLoginService {
    ResponseEntity<?> modifyPassword(String username, String password);
}
