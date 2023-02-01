package com.solution.recipetalk.domain.board.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findBoardByTitle(String title);
}
