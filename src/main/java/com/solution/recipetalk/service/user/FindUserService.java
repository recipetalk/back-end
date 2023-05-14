package com.solution.recipetalk.service.user;

import org.springframework.http.ResponseEntity;

public interface FindUserService {
    ResponseEntity<?> findDuplicatedUsernameInUserLogin(String userName);

    ResponseEntity<?> findDuplicatedNicknameInUserLogin(String nickname);

    ResponseEntity<?> findUserProfile(String username);

    ResponseEntity<?> findMyProfile();
}
