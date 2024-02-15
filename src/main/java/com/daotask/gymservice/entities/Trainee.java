package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trainees")
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    private List<Trainer> trainers;
}
