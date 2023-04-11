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

import java.util.Optional;


public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
    Long countByUser(UserDetail userDetail);
    Long countByFollowing(UserDetail userDetail);
    @Query(value = "SELECT new com.solution.recipetalk.dto.user.UserSimpleProfileDTO(u.username, u.nickname, u.profileImageURI) FROM UserFollow uf JOIN UserDetail u ON uf.following = u WHERE uf.user.id = :id AND u.isDeleted = false AND u.isBlocked = false",

            countQuery = "SELECT COUNT(*) FROM UserFollow f JOIN UserDetail u WHERE f.following = u AND u.id = :id AND u.isDeleted = false AND u.isBlocked = false"
    )
    Page<UserSimpleProfileDTO> findAllByUserPage(@Param("id") Long  id, Pageable pageable);

    Optional<UserFollow> findUserFollowByUserAndFollowing(UserDetail user, UserDetail following);
}
