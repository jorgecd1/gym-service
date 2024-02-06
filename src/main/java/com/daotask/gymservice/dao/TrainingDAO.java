package com.daotask.gymservice.dao;

import com.daotask.gymservice.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingDAO extends JpaRepository<Training, Long> {
}
