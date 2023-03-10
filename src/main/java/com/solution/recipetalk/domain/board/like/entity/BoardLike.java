package com.solution.recipetalk.domain.board.like.entity;


import com.solution.recipetalk.domain.board.like.id.BoardLikeId;
import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="user_board_liked")
@IdClass(BoardLikeId.class)
public class BoardLike extends AuditingEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}
