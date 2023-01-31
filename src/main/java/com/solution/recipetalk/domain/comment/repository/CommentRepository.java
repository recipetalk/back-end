package com.solution.recipetalk.domain.comment.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard(Board board);
    List<Comment> findAllByWriter(UserDetail writer);

    Optional<Comment> findByBoardAndId(Board board, Long commentId);
}
