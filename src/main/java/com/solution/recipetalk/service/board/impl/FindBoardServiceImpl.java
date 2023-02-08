package com.solution.recipetalk.service.board.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BoardFindResponseDTO;
import com.solution.recipetalk.service.board.FindBoardService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FindBoardServiceImpl implements FindBoardService {

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;


    @Override
    public ResponseEntity<?> findAllBoards() {
        List<Board> allBoards = boardRepository.findAll();
        if (allBoards.isEmpty())
            return ResponseEntity.ok("게시물이 존재하지 않습니다.");

        return ResponseEntity.ok(allBoards.stream().map(BoardFindResponseDTO::toResponse).toList());
    }

    @Override

    public ResponseEntity<?> findBoardsContainsTitle(String boardTitle) {

        List<Board> boardsByTitleContains = boardRepository.findBoardsByTitleContains(boardTitle);

        if (boardsByTitleContains.isEmpty())
            return ResponseEntity.ok("게시물이 존재하지 않습니다.");

        return ResponseEntity.ok(boardsByTitleContains.stream().map(BoardFindResponseDTO::toResponse).toList());
    }

    @Override
    public ResponseEntity<?> findBoardsByWriter(String nickname) {
        UserDetail writer = userDetailRepository.findByNickname(nickname).orElse(null);
        if (writer == null) {
            return ResponseEntity.ok("존재하지 않는 사용자입니다.");
        }

        List<Board> boardsByWriter = boardRepository.findByWriter(writer);

        if (boardsByWriter.isEmpty())
            return ResponseEntity.ok("게시물이 존재하지 않습니다.");

        return ResponseEntity.ok(boardsByWriter.stream().map(BoardFindResponseDTO::toResponse).toList());
    }

    @Override
    public ResponseEntity<?> findBoardsByCurrentLoginUser() {
        Long currentUserId = ContextHolder.getUserLoginId();

        UserDetail currentUser = userDetailRepository.findById(currentUserId).orElse(null);

        assert currentUser != null;
        List<Board> boardsByCurrentUser = boardRepository.findByWriter(currentUser);

        if (boardsByCurrentUser.isEmpty())
            return ResponseEntity.ok("게시물이 존재하지 않습니다.");

        return ResponseEntity.ok(boardsByCurrentUser.stream().map(BoardFindResponseDTO::toResponse).toList());
    }

    @Override
    public ResponseEntity<?> findBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);

        if (board == null)
            return ResponseEntity.ok("게시물이 존재하지 않습니다.");

        return ResponseEntity.ok(BoardFindResponseDTO.toResponse(board));
    }
}
