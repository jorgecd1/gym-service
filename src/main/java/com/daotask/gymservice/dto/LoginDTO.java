package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginDTO {
    // Login data
    private String username;
    private String password;
}
