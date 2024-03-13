package com.daotask.gymservice.security.service;

import com.daotask.gymservice.security.jwt.JwtIssuer;
import com.daotask.gymservice.security.jwt.UserPrincipal;
import com.daotask.gymservice.security.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    private int attemptCounter = 0;

    public LoginResponse attemptLogin(String email, String password) throws InterruptedException {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        if(authentication.isAuthenticated() && !waitForTimeout.isAlive()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var principal = (UserPrincipal) authentication.getPrincipal();

            var token = jwtIssuer.issue(JwtIssuer.Request.builder()
                    .userId(principal.getUserId())
                    .username(principal.getUsername())
                    .roles(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .build());

            attemptCounter = 0;

            return LoginResponse.builder()
                    .token(token)
                    .build();
        }
        else if(attemptCounter >= 3){
            attemptCounter = 0;

            waitForTimeout.join();

            return LoginResponse.builder()
                    .token("Password is incorrect. No more attempts, account is blocked for 5 minutes.")
                    .build();
        }
        else {
            attemptCounter++;
            return LoginResponse.builder()
                    .token("Password is incorrect. Attempts remaining ("
                            +attemptCounter+"/3)")
                    .build();
        }
    }
    Thread waitForTimeout = new Thread(){
        @Override
        public void run(){
            try {
                wait(60_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };
}
