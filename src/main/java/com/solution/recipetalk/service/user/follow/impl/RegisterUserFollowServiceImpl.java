package com.solution.recipetalk.service.user.follow.impl;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.entity.UserFollow;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.follow.RegisterUserFollowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserFollowServiceImpl implements RegisterUserFollowService {

    private final UserFollowRepository userFollowRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> registerUserFollow(String followee) {
        UserLogin followeeLogin = userLoginRepository.findByUsername(followee).orElseThrow(UserNotFoundException::new);
        UserLogin followerLogin = ContextHolder.getUserLogin();

        UserDetail followerDetail = userDetailRepository.getReferenceById(followerLogin.getId());

        UserFollow userFollow = UserFollow.builder().follower(followerDetail)
                .followee(followeeLogin.getUserDetail())
                .build();

        userFollowRepository.save(userFollow);

        return ResponseEntity.ok(null);
    }
}
