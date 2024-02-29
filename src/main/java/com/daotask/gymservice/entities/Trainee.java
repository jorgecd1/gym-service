package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Entity
@Table(name = "trainees")
@NoArgsConstructor
@Data
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "address")
    private String address;

    @OneToOne
    private User user;

    @ManyToMany
    private Set<Training> trainers;

    public void addTraining(Training training, Logger logger){
        if(trainers.contains(training)){
            logger.info("Trainer already assigned to this Trainee");
        }
        else {
            logger.info("Added new Trainer to this Trainee");
            trainers.add(training);
        }
    }
}
