package com.daotask.gymservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {

    @GetMapping("/")
    public String landing(){
        return "Hello, world!";
    }
}
