package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateTrainerDTO {
    // Data
    private String username;
    private String firstName;
    private String lastName;
    private String trainingTypeName;
    private boolean isActive;
}
