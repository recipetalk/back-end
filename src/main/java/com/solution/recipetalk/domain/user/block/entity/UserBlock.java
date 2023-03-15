package com.solution.recipetalk.domain.user.block.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.user.block.id.UserBlockId;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_block")
@NoArgsConstructor
@Getter
@SuperBuilder
@IdClass(UserBlockId.class)
public class UserBlock extends AuditingEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_detail_id")
    private UserDetail user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_user_id", nullable = false, referencedColumnName = "user_detail_id")
    private UserDetail blockedUser;
}
