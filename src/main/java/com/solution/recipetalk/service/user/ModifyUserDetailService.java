package com.solution.recipetalk.service.user;

import com.solution.recipetalk.dto.user.UserDetailProfileModifyDTO;
import org.springframework.http.ResponseEntity;

public interface ModifyUserDetailService {
    ResponseEntity<?> modifyUserDetail(UserDetailProfileModifyDTO dto);
}
