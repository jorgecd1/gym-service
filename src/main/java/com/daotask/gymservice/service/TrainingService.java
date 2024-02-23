package com.daotask.gymservice.service;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.dao.TraineeRepository;
import com.daotask.gymservice.dao.TrainerRepository;
import com.daotask.gymservice.dao.TrainingRepository;
import com.daotask.gymservice.dao.TrainingTypeRepository;
import com.daotask.gymservice.dto.NewTrainingDTO;
import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.entities.Training;
import com.daotask.gymservice.entities.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class TrainingService {

    TrainingTypeRepository trainingTypeRepository;
    TrainingRepository trainingRepository;
    TraineeRepository traineeRepository;
    TrainerRepository trainerRepository;
    TraineeService traineeService;
    TrainerService trainerService;
    TrainingTypeService trainingTypeService;


    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());

    @Autowired
    public TrainingService(
            TrainingTypeRepository trainingTypeRepository,
            TrainingRepository trainingRepository,
            TraineeRepository traineeRepository,
            TrainerRepository trainerRepository,
            TraineeService traineeService,
            TrainerService trainerService,
            TrainingTypeService trainingTypeService
    ){
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingRepository = trainingRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingTypeService = trainingTypeService;
    }

    public ResponseEntity<String> add(NewTrainingDTO newTrainingDTO){
        try {
            Optional<Trainee> optionalTrainee = traineeService.traineesByUsername(newTrainingDTO.getTraineeUsername());
            if(optionalTrainee.isEmpty()){
                logger.warning("Couldn't create Training. Missing Trainee");
                return new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST
                );
            }
            Optional<Trainer> optionalTrainer = trainerService.trainerByUsername(newTrainingDTO.getTrainerUsername());
            if(optionalTrainer.isEmpty()){
                logger.warning("Couldn't create Training. Missing Trainer");
                return new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST
                );
            }
            Optional<TrainingType> optionalTrainingType = trainingTypeService.trainingTypeByUsername(newTrainingDTO.getTrainingTypeName());
            if(optionalTrainingType.isEmpty()){
                logger.warning("Couldn't create Training. Missing TrainingType");
                return new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST
                );
            }

            Training training = new Training();

            Trainee trainee = optionalTrainee.get();
            Trainer trainer = optionalTrainer.get();
            TrainingType trainingType = optionalTrainingType.get();

            training.setName(trainingType.getName());
            training.setDate(newTrainingDTO.getTrainingDate());
            training.setDuration(newTrainingDTO.getDuration());
            training.setTrainingType(trainingType);
            training.setTrainee(trainee);
            training.setTrainer(trainer);

            trainingRepository.save(training);

            trainer.addTraining(training, logger);
            trainerRepository.save(trainer);
            trainee.addTraining(training, logger);
            traineeRepository.save(trainee);

            logger.info("Successfully created new Training Entity");
            return new ResponseEntity<>(
                    "Successfully created Training: "+training.toString(),
                    HttpStatus.OK
            );
        }
        catch (Exception e) {
            logger.severe("An error occurred when creating Training");
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
