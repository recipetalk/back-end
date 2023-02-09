package com.solution.recipetalk.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSimpleProfileDTO {
    private String username;
    private String nickname;
    private String profileImageURI;
}
