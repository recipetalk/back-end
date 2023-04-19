package com.solution.recipetalk.domain.board.like.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.entity.BoardLike;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findBoardLikeByBoardAndUser(Board board, UserDetail user);
}
