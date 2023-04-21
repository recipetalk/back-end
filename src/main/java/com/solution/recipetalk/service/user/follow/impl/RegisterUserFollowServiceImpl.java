package com.solution.recipetalk.service.user.follow.impl;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.entity.UserFollow;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.follow.RegisterUserFollowService;
import com.solution.recipetalk.util.ContextHolder;
import com.solution.recipetalk.vo.notification.user.follow.FollowNotificationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserFollowServiceImpl implements RegisterUserFollowService {

    private final UserFollowRepository userFollowRepository;
    private final UserDetailRepository userDetailRepository;

    private final FcmTokenRepository fcmTokenRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ResponseEntity<?> registerUserFollow(String followee) {
        UserDetail followingDetail = userDetailRepository.findUserDetailByUsername(followee).orElseThrow(UserNotFoundException::new);

        UserLogin followerLogin = ContextHolder.getUserLogin();

        UserDetail followerDetail = userDetailRepository.findById(followerLogin.getId()).orElseThrow(UserNotFoundException::new);

        UserFollow userFollow = UserFollow.builder().user(followerDetail)
                .following(followingDetail)
                .build();

        userFollowRepository.save(userFollow);

        Optional<FcmToken> fcmTokenByUser = fcmTokenRepository.findFcmTokenByUser(followingDetail);

        FollowNotificationVO followNotificationVO = FollowNotificationVO.builder()
                .fcmTarget(fcmTokenByUser.orElse(null))
                .user(followerDetail)
                .target(followingDetail)
                .build();

        eventPublisher.publishEvent(followNotificationVO);

        return ResponseEntity.ok(null);
    }
}
