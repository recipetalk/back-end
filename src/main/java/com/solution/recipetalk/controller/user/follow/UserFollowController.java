package com.solution.recipetalk.controller.user.follow;


import com.solution.recipetalk.service.user.follow.FindUserFollowService;
import com.solution.recipetalk.service.user.follow.RegisterUserFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/user/follow")
public class UserFollowController {

    private final RegisterUserFollowService registerUserFollowService;
    private final FindUserFollowService findUserFollowService;
    @PostMapping("/{followee}")
    public ResponseEntity<?> followAdd(@PathVariable(name = "followee") String followee){
        return registerUserFollowService.registerUserFollow(followee);
    }

    @GetMapping("/{followee}")
    public ResponseEntity<?> followingList(@PathVariable(name = "followee") String followee, @PageableDefault(size = 20) Pageable pageable){
        return findUserFollowService.findUserFolloweeList(followee, pageable);
    }
}
