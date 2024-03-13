package com.daotask.gymservice.microservice;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TrainerWorkloadDto implements Serializable {
    // payload data
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private Date trainingDate;
    private int duration;
    private String action;
}
