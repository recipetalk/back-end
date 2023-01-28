package com.solution.recipetalk.service.user;

import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import org.springframework.http.ResponseEntity;

public interface RegisterUserService {
    ResponseEntity<?> addUser(SignUpUserReqDto signUpUserReqDto);
}
