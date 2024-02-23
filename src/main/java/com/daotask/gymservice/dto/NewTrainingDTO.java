package com.daotask.gymservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class NewTrainingDTO {

    // Foreign keys
    private String traineeUsername;
    private String trainerUsername;
    private String trainingTypeName;
    // Data
    private String name;
    private Date trainingDate;
    private int duration;
}
