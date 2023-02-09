package com.solution.recipetalk.domain.user.follow.repository;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.UserFollowId;
import com.solution.recipetalk.domain.user.follow.entity.*;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserFollowRepository extends JpaRepository<UserFollow, UserFollowId> {
    Long countByUser(UserDetail userDetail);

    @Query(value = "SELECT u2.nickname, u2.username, u2.profile_image_uri FROM user_follow f JOIN user_detail u JOIN user_detail u2 WHERE f.user_id = u.id AND f.following_id = u2.id AND u.id = :id AND u2.is_deleted = false",
            countQuery = "SELECT count(*) FROM UserFollow f JOIN UserDetail u WHERE f.followee = u AND u.id = :id",
            nativeQuery = true
    )
    Page<UserSimpleProfileDTO> findAllByUserPage(@Param("id") Long Username, Pageable pageable);
}
