package com.solution.recipetalk.domain.board.entity;

import com.solution.recipetalk.domain.board.like.entity.BoardLike;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "board")
@SQLDelete(sql = "UPDATE board SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Board extends SoftDeleteEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long view_count;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardLike> boardLikes;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public void increaseViewCount() {
        view_count ++;
    }
}
