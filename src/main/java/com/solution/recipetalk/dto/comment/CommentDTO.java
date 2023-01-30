package com.solution.recipetalk.dto.comment;

import com.solution.recipetalk.domain.comment.entity.Comment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotNull
        private Long writerId;

        private Long parentCommentId;

        @NotNull
        @Size(min = 1, max = 1000, message = "댓글은 1000자를 넘어가면 안됩니다.")
        private String description;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String userName;
        private String description;
        private String lastModifiedDate;

        public static Response from(Comment comment) {
            return Response.builder()
                    .userName(comment.getWriter().getNickname())
                    .description(comment.getDescription())
                    .lastModifiedDate(comment.getModifiedDate().toString())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailedResponse {
        private String writerNickname;
        private Long parentCommentId;
        private String description;
        private String lastModifiedDate;

        public static DetailedResponse from(Comment comment) {
            return DetailedResponse.builder()
                    .writerNickname(comment.getWriter().getNickname())
                    .description(comment.getDescription())
                    .lastModifiedDate(comment.getModifiedDate().toString())
                    .build();
        }
    }
}
