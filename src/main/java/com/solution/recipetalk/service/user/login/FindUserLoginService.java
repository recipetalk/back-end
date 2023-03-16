package com.solution.recipetalk.service.user.login;

import org.springframework.http.ResponseEntity;

public interface FindUserLoginService {
    ResponseEntity<?> findDuplicatedEmailInUserLogin(String email);
}
