package com.daotask.gymservice.service;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.Utility;
import com.daotask.gymservice.dao.TrainerRepository;
import com.daotask.gymservice.dao.TrainingTypeRepository;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.dto.*;
import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.entities.TrainingType;
import com.daotask.gymservice.entities.User;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TrainerService {

    TrainerRepository trainerRepository;
    UserRepository userRepository;
    TrainingTypeRepository trainingTypeRepository;
    UserService userService;
    TrainingTypeService trainingTypeService;

    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());

    @Autowired
    public TrainerService(
            TrainerRepository trainerRepository,
            UserRepository userRepository,
            TrainingTypeRepository trainingTypeRepository,
            UserService userService,
            TrainingTypeService trainingTypeService
    ) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.trainingTypeRepository =trainingTypeRepository;
        this.userService = userService;
        this.trainingTypeService = trainingTypeService;
    }

    public ResponseEntity<String> toggleStatus(ToggleActiveStatusDTO statusDTO){
        Optional<Trainer> optionalTrainer = trainerByUsername(statusDTO.getUsername());
        if(optionalTrainer.isPresent()){
            User user = optionalTrainer.get().getUser();
            boolean previousStatus = user.getIsActive();
            if(previousStatus){
                logger.info("Changing status to inactive for Trainer.username: "+statusDTO.getUsername());
                user.setIsActive(false);
                userRepository.save(user);
                return new ResponseEntity<>(
                        "Changed to inactive status",
                        HttpStatus.OK
                );
            }
            else {
                logger.info("Changing status to active for Trainer.username: "+statusDTO.getUsername());
                user.setIsActive(true);
                userRepository.save(user);
                return new ResponseEntity<>(
                        "Changed to active status",
                        HttpStatus.OK
                );
            }
        }
        else {
            logger.info("No Trainer by this username exists, could not toggle status");
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
    public ResponseEntity<String> add(NewTrainerDTO trainerDTO){
        try {
            Utility utility = new Utility();

            Trainer trainer = new Trainer();
            User user = new User();

            // Fetch & Verify TrainingTypeExists
            Optional<TrainingType> trainingType = trainingTypeRepository.findById(trainerDTO.getTrainingTypeId());
            if(trainingType.isPresent()){
                // Set user data
                user.setFirstName(trainerDTO.getFirstName());
                user.setLastName(trainerDTO.getLastName());
                // Generate unique username
                user.setUsername(userService.usernameGenerator(
                        trainerDTO.getFirstName(),
                        trainerDTO.getLastName()
                ));
                user.setPassword(utility.generatePassword());
                user.setIsActive(true);
                // Set Trainee data
                logger.info("Generating and matching new trainer data");
                trainer.setTrainingType(trainingType.get());
                trainer.setUser(user);

                userRepository.save(user);
                trainerRepository.save(trainer);

                logger.info("Successfully saved new Trainer: " + trainer.toString());
                logger.info("Successfully saved new User: " + user.toString());

                return new ResponseEntity<>(
                        "Created Trainer-User:\n"+
                                "Username: " + user.getUsername()+
                                "\nPassword: " + user.getPassword(),
                        HttpStatus.OK
                );
            }
            else {
                logger.info("No training type exists with this id");
                return new ResponseEntity<>(
                        "Failure: No such TrainingType with id#"+trainerDTO.getTrainingTypeId(),
                        HttpStatus.NO_CONTENT
                );
            }
        }
        catch (Exception e) {
            logger.severe("An error occurred when creating new Trainer");
            return new ResponseEntity<>(
                    "An error occurred while saving Trainer-User",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    public ResponseEntity<String> update(UpdateTrainerDTO trainerDTO){
        Optional<Trainer> optionalTrainer = trainerByUsername(trainerDTO.getUsername());
        if(optionalTrainer.isPresent()){

            Trainer trainer = optionalTrainer.get();
            Optional<TrainingType> trainingType = trainingTypeService
                    .trainingTypeByUsername(trainerDTO
                            .getTrainingTypeName());

            if(trainingType.isPresent()){
                trainer.setTrainingType(trainingType.get());

                User user = trainer.getUser();
                user.setFirstName(trainerDTO.getFirstName());
                user.setLastName(trainerDTO.getLastName());
                user.setIsActive(trainerDTO.isActive());

                trainer.setUser(user);

                logger.info("Saving trainer updated info");
                trainerRepository.save(trainer);
                logger.info("Saving user updated info");
                userRepository.save(user);

                Utility utility = new Utility();
                String trainees = utility.traineeFormatter(trainer.getTrainees());

                logger.info("Updated existing Trainer");
                return new ResponseEntity<>(
                        "Username: "+user.getUsername()+",\n"+
                        "firstName: "+user.getFirstName()+",\n"+
                        "lastName: "+user.getLastName()+",\n"+
                        "specialization: "+trainer.getTrainingType().getName()+",\n"+
                        "trainees assigned: \n"+trainees,
                        HttpStatus.OK
                );
            }
            else {
                logger.info("Couldn't update, missing TrainingType data");
                return new ResponseEntity<>(
                        HttpStatus.BAD_REQUEST
                );
            }
        }
        else {
            logger.info("Couldn't update, missing Trainer data");
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    public ResponseEntity<String> getByUsername(String username){
        Optional<Trainer> trainer = trainerByUsername(username);
        if(trainer.isPresent()){
            logger.info("Trainer found with username: "+username);
            User user = trainer.get().getUser();

            Utility utility = new Utility();
            String trainings = utility.trainingFormatter(trainer.get().getTrainees());
            return new ResponseEntity<>(
                    "{username: "+user.getUsername()+",\n"+
                    "firstName: "+user.getFirstName()+",\n"+
                    "lastName: "+user.getLastName()+",\n"+
                    "specialization: "+trainer.get().getTrainingType().getName()+",\n"+
                    "isActive: "+user.getIsActive()+",\n"+
                    "Trainings: \n"+
                    trainings,
                    HttpStatus.OK
            );
        }
        else {
            logger.info("Trainer not found with username: "+username);
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
    public Optional<Trainer> trainerByUsername(String username){
        List<Trainer> trainerList = trainerRepository.findAll();
        for(Trainer trainer : trainerList){
            if(trainer.getUser().getUsername().equals(username)){
                logger.info("Found Trainer-User: "+username);
                return Optional.of(trainer);
            }
        }
        logger.info("Trainer-User not found: "+username);
        return Optional.empty();
    }
}
