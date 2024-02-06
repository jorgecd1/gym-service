package com.daotask.gymservice.repos;

import com.daotask.gymservice.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepo extends JpaRepository<Trainee, Long> {


}
