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
    private Long trainingId; // generated

    // TABLE DATA
    private Date trainingDate; // passed
    private int trainingTime; // passed

    // FOREIGN DATA
    private String trainingName; // fetch

    // FOREIGN KEYS
    private Long trainerId; // passed
    private Long traineeId; // passed
    private Long trainingTypeId; //passed
}
