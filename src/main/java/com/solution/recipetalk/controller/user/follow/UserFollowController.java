package com.solution.recipetalk.controller.user.follow;


import com.solution.recipetalk.service.user.follow.FindUserFollowService;
import com.solution.recipetalk.service.user.follow.RegisterUserFollowService;
import com.solution.recipetalk.service.user.follow.RemoveUserFollowService;
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

    private final RemoveUserFollowService removeUserFollowService;

    @PostMapping("/{following}")
    public ResponseEntity<?> followAdd(@PathVariable(name = "following") String following){
        return registerUserFollowService.registerUserFollow(following);
    }

    @GetMapping("/{following}")
    public ResponseEntity<?> followingList(@PathVariable(name = "following") String following, @PageableDefault(size = 20) Pageable pageable){
        return findUserFollowService.findUserFolloweeList(following, pageable);
    }

    @DeleteMapping("/{following}")
    public ResponseEntity<?> followingRemove(@PathVariable(name = "following") String following) {
        return removeUserFollowService.removeUserFollow(following);
    }
}
