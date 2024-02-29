package com.daotask.gymservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class NewTraineeDTO {

    // Trainee Data
    private Date dateOfBirth;
    private String address;
    // User Data
    private String firstName;
    private String lastName;
}
