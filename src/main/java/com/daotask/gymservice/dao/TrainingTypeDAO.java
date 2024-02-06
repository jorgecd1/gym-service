package com.daotask.gymservice.dao;

import com.daotask.gymservice.entities.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeDAO extends JpaRepository<TrainingType,Long> {
}
