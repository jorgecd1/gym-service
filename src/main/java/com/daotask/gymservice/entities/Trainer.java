package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "trainers")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TrainingType trainingType;
    @OneToOne
    private User user;

    @ManyToMany
    private List<Trainee> trainees;
}
