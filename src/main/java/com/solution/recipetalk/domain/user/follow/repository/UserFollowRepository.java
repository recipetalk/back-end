package com.solution.recipetalk.domain.user.follow.repository;

import com.solution.recipetalk.domain.user.follow.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
}
