package com.solution.recipetalk.controller.user;


import com.solution.recipetalk.dto.user.UserDetailProfileModifyDTO;
import com.solution.recipetalk.service.user.FindUserService;
import com.solution.recipetalk.service.user.ModifyUserDetailService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {

    private final FindUserService findUserService;

    private final ModifyUserDetailService modifyUserDetailService;

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> profileDetails(@PathVariable @NonNull String username){
        return findUserService.findUserProfile(username);
    }

    @PatchMapping("/profile")
    public ResponseEntity<?> profileModify(@Valid UserDetailProfileModifyDTO dto){
        return modifyUserDetailService.modifyUserDetail(dto);
    }

}
