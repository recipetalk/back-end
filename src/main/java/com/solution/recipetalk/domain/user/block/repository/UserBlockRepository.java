package com.solution.recipetalk.domain.user.block.repository;

import com.solution.recipetalk.domain.user.block.entity.UserBlock;
import com.solution.recipetalk.domain.user.block.id.UserBlockId;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserBlockRepository extends JpaRepository<UserBlock, UserBlockId> {


    @Query(value = "SELECT new com.solution.recipetalk.dto.user.UserSimpleProfileDTO(u.username, u.nickname, u.profileImageURI) FROM UserBlock ub JOIN UserDetail u ON ub.blockedUser = u WHERE ub.user.id = :userId",
    countQuery = "SELECT COUNT(*) FROM UserBlock ub JOIN UserDetail u ON ub.blockedUser = u WHERE ub.user.id = :userId")
    Page<UserSimpleProfileDTO> findByUserId(Long userId, Pageable pageable);

    Optional<UserBlock> findByUserAndBlockedUser(UserDetail user, UserDetail blockedUser);
}
