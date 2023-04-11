package com.solution.recipetalk.domain.comment.repository;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.dto.comment.CommentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * @param boardId
     * @param pageable
     * @return CommentResponseDTO
     * - 최상위 덧글은 삭제여부 상관없이 모두 조회. 그러나 삭제여부 판단은 DTO에서 판단해야 함.
     */
    @Query(value = "SELECT new com.solution.recipetalk.dto.comment.CommentResponseDTO(writer.username, writer.nickname, writer.profileImageURI, c.id, c.description, c.createdDate, c.createdDate <> c.modifiedDate as Modified, c.childComment is not empty as childExist, c.isDeleted, writer.isDeleted) FROM Comment c JOIN UserDetail writer ON c.writer = writer WHERE writer.isBlocked = FALSE AND c.board.id = :boardId AND writer.isDeleted = FALSE AND writer.id NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewerId) AND c.parentComment.id = null ORDER BY c.id",
            countQuery = "SELECT count(*) FROM Comment c JOIN UserDetail writer ON c.writer = writer WHERE writer.isBlocked = FALSE AND c.board.id = :boardId AND writer.isDeleted = false AND writer.id NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewerId) ORDER BY c.id")
    Page<CommentResponseDTO> findAllParentCommentByBoard(Long boardId, Pageable pageable, Long viewerId);

    @Query(value = "SELECT new com.solution.recipetalk.dto.comment.CommentResponseDTO(writer.username, writer.nickname, writer.profileImageURI, c.id, c.description, c.createdDate, c.createdDate <> c.modifiedDate as Modified, c.isDeleted, writer.isDeleted) FROM Comment c JOIN UserDetail writer ON c.writer = writer WHERE c.parentComment.Id = :parentCommentId AND writer.isBlocked = FALSE AND c.board.id = :boardId AND writer.isDeleted = FALSE AND writer.id NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewerId) ORDER BY c.id",
            countQuery = "SELECT COUNT(*) FROM Comment c JOIN UserDetail writer ON c.writer = writer WHERE c.parentComment.Id = :parentCommentId AND writer.isBlocked = FALSE AND c.board.id = :boardId AND writer.isDeleted = FALSE AND writer.id NOT IN (SELECT blockedUser FROM UserBlock WHERE user.id = :viewerId) ORDER BY c.id"
    )
    Page<CommentResponseDTO> findAllChildCommentByBoard(Long viewerId, Long boardId, Long parentCommentId, Pageable pageable);

    @Query(value = "SELECT new com.solution.recipetalk.dto.comment.CommentResponseDTO(c.id, b.id,  c.description, c.createdDate, c.createdDate <> c.modifiedDate as Modified, b.boardSort ) FROM Comment c JOIN UserDetail writer ON c.writer = writer JOIN Board b ON c.board = b WHERE writer.id = :userId AND c.isDeleted = false ORDER BY c.id",
            countQuery = "SELECT COUNT(*) FROM Comment c JOIN UserDetail writer ON c.writer = writer WHERE writer.id = :userId AND c.isDeleted = false"
    )
    Page<CommentResponseDTO> findAllByWriter(Long userId, Pageable pageable);

    Optional<Comment> findByBoardAndId(Board board, Long commentId);

    void deleteAllByBoard(Board board);
}
