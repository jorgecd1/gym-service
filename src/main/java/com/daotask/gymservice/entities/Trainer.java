package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="trainers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trainerId;

    // TABLE DATA
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;

    private String specialization;
}
