package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "trainings")
@NoArgsConstructor
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "training_name", nullable = false)
    private String name;
    @Column(name = "training_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "training_duration", nullable = false)
    private int duration;

    @ManyToOne
    private TrainingType trainingType;
    @ManyToOne
    private Trainee trainee;
    @ManyToOne
    private Trainer trainer;
}
