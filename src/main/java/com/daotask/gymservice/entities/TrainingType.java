package com.daotask.gymservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="trainingType")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trainingTypeId;

    // TABLE DATA
    private String trainingTypeName;
}
