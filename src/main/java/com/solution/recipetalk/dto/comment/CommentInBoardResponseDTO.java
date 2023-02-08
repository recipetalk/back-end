package com.solution.recipetalk.dto.comment;

import com.solution.recipetalk.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentInBoardResponseDTO {
    private String writerNickname;
    private Long parentCommentId;
    private String description;
    private String lastModifiedDate;

    public static CommentInBoardResponseDTO toResponse(Comment comment) {
        return CommentInBoardResponseDTO.builder()
                .writerNickname(comment.getWriter().getNickname())
                .description(comment.getDescription())
                .lastModifiedDate(comment.getModifiedDate().toString())
                .build();
    }
}
