package com.daotask.gymservice.controller;

import com.daotask.gymservice.dto.LoginDTO;
import com.daotask.gymservice.dto.NewLoginDTO;
import com.daotask.gymservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-login")
public class LoginController {

    UserService userService;

    public LoginController(
            UserService userService
    ){
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
    @PutMapping("/change-login")
    public ResponseEntity<String> changeLogin(@RequestBody NewLoginDTO newLoginDTO){
        return userService.changeLogin(newLoginDTO);
    }
}
