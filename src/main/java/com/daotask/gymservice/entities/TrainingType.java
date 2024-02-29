package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "training_types")
@NoArgsConstructor
@Data
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "training_type_name", nullable = false)
    private String name;
}
