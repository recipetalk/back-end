package com.solution.recipetalk.controller.user.follow;


import com.solution.recipetalk.service.user.follow.RegisterUserFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/user/follow")
public class UserFollowController {

    private final RegisterUserFollowService registerUserFollowService;

    @PostMapping("/{followee}")
    public ResponseEntity<?> followAdd(@PathVariable(name = "followee") String followee){
        return registerUserFollowService.registerUserFollow(followee);
    }
}
