package com.solution.recipetalk.domain.user.follow.repository;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.entity.*;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {
    @Query(value = "SELECT COUNT(*) FROM UserFollow WHERE user = :userDetail AND following.isDeleted = false AND following.isBlocked = false AND following NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewerId)")
    Long countByUser(UserDetail userDetail, Long viewerId);
    @Query(value = "SELECT COUNT(*) FROM UserFollow WHERE following = :userDetail AND user.isDeleted = false AND user.isBlocked = false AND user NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewerId)")
    Long countByFollowing(UserDetail userDetail, Long viewerId);
    @Query(value = "SELECT new com.solution.recipetalk.dto.user.UserSimpleProfileDTO(u.username, u.nickname, u.profileImageURI, u.description) FROM UserFollow uf JOIN UserDetail u ON uf.following = u WHERE uf.user.id = :id AND u.isDeleted = false AND u.isBlocked = false AND u NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewer)",

            countQuery = "SELECT COUNT(*) FROM UserFollow f JOIN UserDetail u WHERE f.following = u AND u.id = :id AND u.isDeleted = false AND u.isBlocked = false AND u NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewer)"
    )
    Page<UserSimpleProfileDTO> findAllByUserPage(@Param("id") Long id, @Param("viewer") Long viewer, Pageable pageable);

    Optional<UserFollow> findUserFollowByUserAndFollowing(UserDetail user, UserDetail following);

    @Query(value = "SELECT new com.solution.recipetalk.dto.user.UserSimpleProfileDTO(u.username, u.nickname, u.profileImageURI, u.description) FROM UserFollow uf JOIN UserDetail u ON uf.user = u WHERE uf.following.id = :id AND u.isDeleted = false AND u.isBlocked = false AND u.isDeleted = false AND u NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewer)",

            countQuery = "SELECT COUNT(*) FROM UserFollow f JOIN UserDetail u WHERE f.following = u AND u.id = :id AND u.isDeleted = false AND u.isBlocked = false  AND u NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :id)"
    )
    Page<UserSimpleProfileDTO> findAllByFollowingPage(@Param("id") Long  id, @Param("viewer") Long viewer, Pageable pageable);
}
