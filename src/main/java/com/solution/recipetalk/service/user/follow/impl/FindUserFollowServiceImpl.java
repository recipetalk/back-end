package com.solution.recipetalk.service.user.follow.impl;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.follow.FindUserFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindUserFollowServiceImpl implements FindUserFollowService {
    private final UserFollowRepository followRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> findUserFolloweeList(String username, Pageable pageable) {
        UserDetail followee = userDetailRepository.findUserDetailByUsername(username).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(
                followRepository.findAllByUserPage(followee.getId(), pageable)
        );
    }
}
