package com.daotask.gymservice.service;

import com.daotask.gymservice.dao.TraineeRepository;
import com.daotask.gymservice.dao.TrainerRepository;
import com.daotask.gymservice.dao.TrainingRepository;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.entities.Trainee;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraineeService {

    private TrainerRepository trainerRepository;
    private TrainingRepository trainingRepository;
    private TraineeRepository traineeRepository;
    private UserService userService;

    public TraineeService(
            TraineeRepository traineeRepository,
            TrainingRepository trainingRepository,
            TrainerRepository trainerRepository,
            UserService userService
    ){
        this.traineeRepository = traineeRepository;
        this.trainingRepository = trainingRepository;
        this.trainerRepository = trainerRepository;
        this.userService = userService;
    }

    private void authenticateTrainee(String username, String password){
        Trainee trainee = getTraineeByUsername(username);
        if(trainee.getUser().getPassword().equals(password)){
            // USER IS LOGGED
        }
        else{
            // USER IS NOT LOGGED THROW NOT AUTH
        }
    }

    public Trainee getTraineeByUsername(String username){
        Optional<Trainee> trainee = traineeRepository.findByUsername(username);
        if(trainee.isPresent()){
            return trainee.get();
        }
        else{
            return null;
        }
    }
}
