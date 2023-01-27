package com.solution.recipetalk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello(){
        throw new RuntimeException("test");
        //return "hello";
    }
}
