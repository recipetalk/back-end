package com.solution.recipetalk.controller.user;


import com.solution.recipetalk.service.user.FindUserService;
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

    @Autowired
    private final FindUserService findUserService;


    @GetMapping("/profile/{username}")
    public ResponseEntity<?> profileDetails(@PathVariable @NonNull String username){
        return findUserService.findUserProfile(username);
    }
}
