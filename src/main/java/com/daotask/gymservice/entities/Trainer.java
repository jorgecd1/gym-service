package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

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
    private Set<Training> trainees;

    public void addTraining(Training training, Logger logger){
        if(trainees.contains(training)){
            logger.info("Training already assigned to this Trainer");
        }
        else {
            logger.info("Added new Training to this Trainer");
            trainees.add(training);
        }
    }
}
