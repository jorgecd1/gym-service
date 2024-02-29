package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NewLoginDTO {
    // Login Data
    private String username;
    private String oldPassword;
    private String newPassword;
}
