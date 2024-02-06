package com.daotask.gymservice.dao;

import com.daotask.gymservice.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerDAO extends JpaRepository<Trainer, Long> {
}
