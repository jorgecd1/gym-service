package com.daotask.gymservice.dao;

import com.daotask.gymservice.entities.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeDAO extends JpaRepository<Trainee, Long> {

}
