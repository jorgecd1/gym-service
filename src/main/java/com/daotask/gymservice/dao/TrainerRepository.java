package com.daotask.gymservice.dao;

import com.daotask.gymservice.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByUsername(String username);
    List<Trainer> findAll();

    List<Trainer> findAllByUsername(List<String> trainerUsername);
}
