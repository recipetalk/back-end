package com.solution.recipetalk.domain.board.like.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<Board, Long> {
}
