package com.solution.recipetalk.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;


@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private UserSimpleProfileDTO userProfile;
    private Long commentId;
    private Long boardId;
    private String description;
    private String createdDate;
    private Boolean isModified;
    private Boolean childExist;

    public CommentResponseDTO(String username, String nickname, String profileImageURI, Long commentId, Long boardId,
                              String description, String createdDate, Boolean isModified,
                              Boolean childExist, Boolean isDeleted){
        if(isDeleted){
            userProfile = new UserSimpleProfileDTO("삭제", "(삭제)", "");
            this.commentId = commentId;
            this.boardId = boardId;
            this.description = "삭제된 덧글입니다.";
            this.createdDate = createdDate;
            this.childExist = childExist;
        }
        else {
            userProfile = new UserSimpleProfileDTO(username, nickname, profileImageURI);
            this.commentId = commentId;
            this.boardId = boardId;
            this.description = description;
            this.createdDate = createdDate;
            this.isModified = isModified;
            this.childExist = childExist;
        }
    }

    public static CommentResponseDTOBuilder builder() {
        return new CommentResponseDTOBuilder();
    }

    @ToString
    public static class CommentResponseDTOBuilder {
        private UserSimpleProfileDTO userProfile;
        private Long commentId;
        private Long boardId;
        private String description;
        private String createdDate;
        private Boolean isModified;
        private Boolean childExist;
        private Boolean isDeleted;

        CommentResponseDTOBuilder() {
        }

        public CommentResponseDTOBuilder userProfile(final UserSimpleProfileDTO userProfile) {
            if(userProfile == null)
                this.userProfile = userProfile;
            return this;
        }

        public CommentResponseDTOBuilder boardId(final Long boardId) {
            this.boardId = boardId;
            return this;
        }
        public CommentResponseDTOBuilder commentId(final Long commentId) {
            this.commentId = commentId;
            return this;
        }
        public CommentResponseDTOBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public CommentResponseDTOBuilder isModified(final Boolean isModified) {
            this.isModified = isModified;
            return this;
        }

        public CommentResponseDTOBuilder createdDate(final String createdDate){
            this.createdDate = createdDate;
            return this;
        }

        public CommentResponseDTOBuilder childExist(final Boolean childExist){
            this.childExist = childExist;
            return this;
        }

        public CommentResponseDTOBuilder isDeleted(final Boolean isDeleted){
            this.isDeleted = isDeleted;
            return this;
        }
        public CommentResponseDTO build() {
            return new CommentResponseDTO(userProfile.getUsername(), userProfile.getNickname(), userProfile.getProfileImageURI(), commentId, boardId, description, createdDate, isModified, childExist, isDeleted);
        }
    }
}
