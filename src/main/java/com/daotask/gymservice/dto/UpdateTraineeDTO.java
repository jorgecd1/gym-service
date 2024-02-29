package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UpdateTraineeDTO {
    // Login Data
    private String username;
    // Data
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private boolean active;
}
