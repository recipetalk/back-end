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
    @Query(value = "SELECT u.username, u.nickname, u.profile_image_uri, c.comment_id, c.board_id c.description, c.created_date, c.modified_date <> c.created_date as isModified, " +
                        "EXISTS(SELECT c.id FROM comment c JOIN comment p ON c.parent_comment_id = p.comment_id) as childExist, c.is_deleted as isDeleted " +
                                "FROM comment c JOIN user_detail u ON c.writer_id = u.user_detail_id " +
                                "WHERE c.board_id = :boardId",
            countQuery = "SELECT COUNT(*) FROM comment c JOIN user_detail u ON c.writer_id = u.user_detail_id WHERE c.board_id = :boardId",
            nativeQuery = true
    )
    Page<CommentResponseDTO> findAllParentCommentByBoard(Long boardId, Pageable pageable);

    @Query(value = "SELECT u.username as username, u.nickname as nickname, u.profile_image_uri as profileImageURI, c.comment_id as commentId, c.board_id as boardId, c.description as description, c.created_date as createdDate, IF(c.modified_date <> c.created_date) as isModified, " +
            "FROM comment c JOIN user_detail u ON c.writer_id = u.user_detail_id "+
                             " JOIN comment pc ON c.parent_coment_id = pc.comment_id " +
            "WHERE c.board_id = :boardId AND c.parent_comment_id = :parentCommentId",
            countQuery = "SELECT COUNT(*) FROM comment c JOIN user_detail u ON c.writer_id = u.user_detail_id JOIN comment pc ON c.parent_comment_id = pc.comment_id WHERE c.board_id = :boardId AND c.parent_comment_id = :parentCommentId",
            nativeQuery = true
    )
    Page<CommentResponseDTO> findAllChildCommentByBoard(Long boardId, Long parentCommentId, Pageable pageable);

    @Query(value = "SELECT u.username, u.nickname, u.profile_image_uri, c.board_id, c.comment_id, c.description, c.created_date, IF(c.modified_date <> c.created_date) as is_modified " +
            "FROM comment c JOIN user_detail u ON c.writer_id = u.user_detail_id " +
            "WHERE c.writer_id = :userId AND c.is_deleted = false",
            countQuery = "SELECT COUNT(*) FROM comment c JOIN user_detail u ON c.writer_id = u.user_detail_id WHERE c.writer_id = :userId AND is_deleted = false",
            nativeQuery = true
    )
    Page<CommentResponseDTO> findAllByWriter(Long userId, Pageable pageable);

    Optional<Comment> findByBoardAndId(Board board, Long commentId);

    void deleteAllByBoard(Board board);
}
