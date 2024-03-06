package com.daotask.gymservice.security;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.security.model.LoginRequest;
import com.daotask.gymservice.security.model.LoginResponse;
import com.daotask.gymservice.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) throws InterruptedException {
        logger.info("Calling service");
        return authService.attemptLogin(request.getUsername(), request.getPassword());
    }
}
