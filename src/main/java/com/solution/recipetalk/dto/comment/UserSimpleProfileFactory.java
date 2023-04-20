package com.solution.recipetalk.dto.comment;

import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;

public class UserSimpleProfileFactory {

    public static UserSimpleProfileDTO isDeleted(){
        return new UserSimpleProfileDTO(null, "(삭제)", "");
    }

    public static UserSimpleProfileDTO isWithdraw() {
        return new UserSimpleProfileDTO(null, "(삭제)", "");
    }
}
