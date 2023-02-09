package com.solution.recipetalk.domain.user.follow.repository;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.UserFollowId;
import com.solution.recipetalk.domain.user.follow.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserFollowRepository extends JpaRepository<UserFollow, UserFollowId> {
    Long countByFollowee(UserDetail userDetail);
}
