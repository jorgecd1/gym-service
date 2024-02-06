package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="trainees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long traineeId;

    // Data
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;

    // TODO Implement data origin from USER
    private String dateOfBirth;
    private String address;
}
