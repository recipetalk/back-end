package com.solution.recipetalk.service.user.follow.impl;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.UserFollowId;
import com.solution.recipetalk.domain.user.follow.entity.UserFollow;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.common.NotFoundException;
import com.solution.recipetalk.exception.user.UserFollowNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.follow.RemoveUserFollowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveUserFollowServiceImpl implements RemoveUserFollowService {
    private final UserFollowRepository userFollowRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> removeUserFollow(String followingUsername) {
        String sessionId = ContextHolder.getUsername();
        UserDetail loginSession = userDetailRepository.findUserDetailByUsername(sessionId)
                .orElseThrow(UserNotFoundException::new);

        UserDetail followingUser = userDetailRepository.findUserDetailByUsername(followingUsername)
                .orElseThrow(UserNotFoundException::new);

        UserFollow findFollowing = userFollowRepository.findUserFollowByUserAndFollowing(loginSession, followingUser)
                .orElseThrow(UserFollowNotFoundException::new);

        userFollowRepository.delete(findFollowing);

        return ResponseEntity.ok(null);
    }
}
