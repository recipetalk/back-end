package com.solution.recipetalk.service.user.follow;

import org.springframework.http.ResponseEntity;

public interface RemoveUserFollowService {
    ResponseEntity<?> removeUserFollow(String followingUsername);
}
