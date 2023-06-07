package com.solution.recipetalk.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailProfileModifyDTO {

    private String nickname;
    private String description;
    private MultipartFile profileImg;
    private Boolean isProfileImgDeleted;

}
