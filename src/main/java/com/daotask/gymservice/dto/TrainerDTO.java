package com.daotask.gymservice.dto;

import com.daotask.gymservice.entities.TrainingType;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class TrainerDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private TrainingType specialization;
}
