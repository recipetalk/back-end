package com.solution.recipetalk.domain.board.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByWriter(UserDetail writer);
    List<Board> findBoardsByTitleContains(String title);
}
