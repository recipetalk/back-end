package com.solution.recipetalk.domain.board.bookmark.entity;

import com.solution.recipetalk.domain.board.bookmark.id.BookmarkId;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bookmark")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Bookmark extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private UserDetail user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="board_id", nullable = false)
    private Board board;
}
