package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainers")
@NoArgsConstructor
@Data
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TrainingType trainingType;
    @OneToOne
    private User user;

    @ManyToMany
    private Set<Trainee> trainees;
}
