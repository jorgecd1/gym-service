package com.daotask.gymservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JWTIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var token = jwtIssuer.issue(1L,request.getUsername(), List.of("USER"));
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

    @GetMapping("/secured")
    public String secured(){
        return "If you see this you are logged in";
    }
}
