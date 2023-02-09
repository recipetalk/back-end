package com.solution.recipetalk.service.user.follow;


import org.springframework.http.ResponseEntity;

public interface RegisterUserFollowService {
    ResponseEntity<?> registerUserFollow(String followee);
}
