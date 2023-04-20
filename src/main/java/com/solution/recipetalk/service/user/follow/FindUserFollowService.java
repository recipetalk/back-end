package com.solution.recipetalk.service.user.follow;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindUserFollowService {
    ResponseEntity<?> findUserFolloweeList(String username, Pageable pageable);

    ResponseEntity<?> findUserFollowerList(String username, Pageable pageable);

    ResponseEntity<?> findUserFollow(String username);
}
