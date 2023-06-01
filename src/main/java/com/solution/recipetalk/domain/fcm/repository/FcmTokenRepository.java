package com.solution.recipetalk.domain.fcm.repository;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {

    Optional<FcmToken> findFcmTokenByUser(UserDetail userDetail);

    void deleteAllByUser_Id(Long userId);

    void deleteFcmTokenByUser_Id(Long userId);
}
