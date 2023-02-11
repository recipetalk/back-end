package com.solution.recipetalk.dto.comment;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDTO {
    private Long parentCommentId;

    @NonNull
    @Size(min = 1, max = 1000, message = "댓글은 1000자를 넘어가면 안됩니다.")
    private String description;

    public Comment toEntity(UserDetail writer, Comment parrentComment, Board board){
        return Comment.builder()
                .writer(writer)
                .parentComment(
                        // parentComment가 존재한다 = 대댓글
                        // 존재하지 않는다(null 이다) = 그냥 댓글
                        parrentComment
                )
                .board(board)
                .description(description)
                .build();
    }
}
