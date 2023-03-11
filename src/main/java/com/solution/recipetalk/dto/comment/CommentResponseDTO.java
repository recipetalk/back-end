package com.solution.recipetalk.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

import java.time.LocalDateTime;




@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private UserSimpleProfileDTO userProfile;
    private Long commentId;
    private String description;
    private LocalDateTime createdDate;
    private Boolean isModified;
    private Boolean childExist;

    public CommentResponseDTO(String username, String nickname, String profileImageURI, Long commentId,
                              String description, LocalDateTime createdDate, Boolean isModified,
                              Boolean childExist, Boolean isDeleted, Boolean isUserDeleted){

        if(isDeleted){
            userProfile = new UserSimpleProfileDTO(null, "(삭제)", "");
            this.description = "삭제된 덧글입니다.";
        }
        if(isUserDeleted){
            userProfile = new UserSimpleProfileDTO(null, "(알수 없음)", "");
        }

        if(!isDeleted && !isUserDeleted) userProfile = new UserSimpleProfileDTO(username, nickname, profileImageURI);
        this.commentId = commentId;
        if(!isDeleted)  this.description = description;
        this.createdDate = createdDate;
        this.isModified = isModified;
        this.childExist = childExist;
    }
}
