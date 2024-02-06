package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="trainings")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trainingId;

    // TABLE DATA
    private Date trainingDate;
    private int trainingTime;

    // FOREIGN DATA
    private String trainingName;

    // FOREIGN KEYS
    private Long trainerId;
    private Long traineeId;
    private Long trainingTypeId;
}
