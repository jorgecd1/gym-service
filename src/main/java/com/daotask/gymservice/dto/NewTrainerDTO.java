package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NewTrainerDTO {

    // Trainer Data
    private Long trainingTypeId;

    // User Data
    private String firstName;
    private String lastName;
}
