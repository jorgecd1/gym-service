package com.daotask.gymservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

// add component annotation
@Entity
@Table(name="Trainees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
}
