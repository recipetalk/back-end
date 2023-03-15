package com.solution.recipetalk.domain.board.like.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.entity.BoardLike;
import com.solution.recipetalk.domain.board.like.id.BoardLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, BoardLikeId> {
}
