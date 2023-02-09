package com.solution.recipetalk.domain.user.follow.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;

import com.solution.recipetalk.domain.user.follow.UserFollowId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_follow")
@IdClass(UserFollowId.class)
public class UserFollow extends AuditingEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_detail_id")
    private UserDetail user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", referencedColumnName = "user_detail_id")
    private UserDetail following;


}
