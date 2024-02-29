package com.daotask.gymservice.dao;

import com.daotask.gymservice.entities.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Long> {
}
