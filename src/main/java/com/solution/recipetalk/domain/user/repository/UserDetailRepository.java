package com.solution.recipetalk.domain.user.repository;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByPhoneNum(String phoneNum);

    Optional<UserDetail> findByNickname(String nickname);
}
