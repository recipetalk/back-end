package com.solution.recipetalk.domain.fcm.entity.temp.repository;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempFcmTokenRepository extends JpaRepository<TempFcmToken, Long> {

    Optional<TempFcmToken> findTempFcmTokenByEmail(String email);
}
