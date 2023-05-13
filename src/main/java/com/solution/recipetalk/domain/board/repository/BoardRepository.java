package com.solution.recipetalk.domain.board.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByWriter(UserDetail writer);
    List<Board> findBoardsByTitleContains(String title);

    @Modifying
    @Query(value = "DELETE FROM board WHERE board_id = :boardId", nativeQuery = true)
    void hardDeleteBoardByboardId(@Param("boardId")Long boardId);
}
