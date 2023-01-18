package com.solution.recipetalk.controller.user;


import com.solution.recipetalk.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final FindUserService findUserService;

    @GetMapping("/signup/{id}")
    public ResponseEntity<?> duplicatedUserCheck(@PathVariable("id")String userName) {
        return findUserService.findDuplicatedUsernameInUserLogin(userName);
    }
}
