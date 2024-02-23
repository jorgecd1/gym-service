package com.daotask.gymservice.service;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.Utility;
import com.daotask.gymservice.dao.TraineeRepository;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.dto.NewTraineeDTO;
import com.daotask.gymservice.dto.UpdateTraineeDTO;
import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TraineeService {

    TraineeRepository traineeRepository;
    UserRepository userRepository;
    UserService userService;

    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());

    @Autowired
    public TraineeService(
            TraineeRepository traineeRepository,
            UserRepository userRepository,
            UserService userService
    ){
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    // Should support create, read, update and delete operations
    public ResponseEntity<String> add(NewTraineeDTO traineeDTO) {
        try {
            Utility utility = new Utility();

            Trainee trainee = new Trainee();
            User user = new User();

            // Set user data
            logger.info("Saving new user data");
            user.setFirstName(traineeDTO.getFirstName());
            user.setLastName(traineeDTO.getLastName());
            // Generate unique username
            user.setUsername(userService.usernameGenerator(
                    traineeDTO.getFirstName(),
                    traineeDTO.getLastName()
            ));
            user.setPassword(utility.generatePassword());
            user.setIsActive(true);
            // Set trainee data
            logger.info("Generating and matching new trainee data");
            trainee.setAddress(traineeDTO.getAddress());
            trainee.setDateOfBirth(traineeDTO.getDateOfBirth());
            trainee.setUser(user);

            userRepository.save(user);
            traineeRepository.save(trainee);

            logger.info("Successfully saved new Trainee: "+trainee.toString());
            logger.info("Successfully saved new User: "+user.toString());

            return new ResponseEntity<>(
                    "Created Trainee-User: \n"+
                    "Username: "+user.getUsername()+"\n"+
                    "Password: "+user.getPassword(),
                    HttpStatus.OK
            );
        }
        catch (Exception e) {
            logger.severe("An error occurred when creating new Trainee-User Entity");
            return new ResponseEntity<>(
                    "An error occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    public ResponseEntity<String> getByUsername(String username){
        List<Trainee> trainees = traineeRepository.findAll();
        for(Trainee trainee : trainees){
            if(trainee.getUser().getUsername().equals(username)){
                // Found
                User user = trainee.getUser();
                return new ResponseEntity<>(
                  "Found trainee: \n"+
                   user.getFirstName()+"\n"+
                   user.getLastName()+"\n"+
                        trainee.getDateOfBirth()+"\n"+
                        trainee.getAddress()+"\n"+
                        user.getIsActive()+"\n",
                   HttpStatus.OK
                );
            }
        }
        // Not found
        logger.info("No trainee by that username");
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }
    public ResponseEntity<String> get(Long id){
        try {
            Optional<Trainee> trainee = traineeRepository.findById(id);
            if(trainee.isPresent()){
                logger.info("Successfully retrieved Trainee-User with id:"+id);
                return new ResponseEntity<>(
                        "Retrieved: "+
                        trainee.get().toString()+
                        trainee.get().getUser().toString(),
                        HttpStatus.OK
                );
            }
            else {
                logger.info("No Trainee-User was found with id#"+id);
                return new ResponseEntity<>(
                        HttpStatus.NOT_FOUND
                );
            }
        }
        catch (Exception e) {
            logger.severe(
                    "A severe error occurred when calling TraineeService.get"
            );
            return new ResponseEntity<>(
                    "An error occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    public ResponseEntity<List<String>> getAll(){
        try {
            List<Trainee> trainees = traineeRepository.findAll();
            if(trainees.isEmpty()){
                logger.info("No trainees were found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                List<String> responseBody = new ArrayList<>();
                for(Trainee trainee : trainees){
                    User user = trainee.getUser();
                    responseBody.add(
                            "Trainee: {" +
                                    trainee.getDateOfBirth()+", "+
                                    trainee.getAddress()+", "+
                                    user.getUsername()+", "+
                                    user.getIsActive()+
                            "}"
                    );
                }
                return new ResponseEntity<>(
                        responseBody,
                        HttpStatus.OK
                );
            }
        }
        catch (Exception e) {
            logger.severe("An error occurred at: TraineeService.getAll");
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    public ResponseEntity<String> update(UpdateTraineeDTO traineeDTO){
        Optional<Trainee> trainee = traineesByUsername(traineeDTO.getUsername());
        if(trainee.isPresent()){
            User user = trainee.get().getUser();
            Trainee t = trainee.get();

            t.setDateOfBirth(traineeDTO.getDateOfBirth());
            t.setAddress(traineeDTO.getAddress());
            user.setFirstName(traineeDTO.getFirstName());
            user.setLastName(traineeDTO.getLastName());
            user.setIsActive(traineeDTO.isActive());

            t.setUser(user);

            try {
                logger.info("Updated Trainee-User with name: "+user.getUsername());
                traineeRepository.save(t);
                userRepository.save(user);
                return new ResponseEntity<>(
                        "Found trainee: \n"+
                                user.getFirstName()+"\n"+
                                user.getLastName()+"\n"+
                                t.getDateOfBirth()+"\n"+
                                t.getAddress()+"\n"+
                                user.getIsActive()+"\n",
                        HttpStatus.OK
                );
            }
            catch (Exception e) {
                logger.severe("An error occurred when updating a Trainee-User");
                return new ResponseEntity<>(
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }
        else {
            logger.info("No Trainee with that username was found");
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
    public ResponseEntity<String> delete(String username){
        Optional<Trainee> trainee = traineesByUsername(username);
        if(trainee.isPresent()){
            try {
                logger.info("Deleting Trainee-User by username: "+username);
                userRepository.delete(trainee.get().getUser());
                traineeRepository.delete(trainee.get());

                return new ResponseEntity<>(
                        "Successfully deleted Trainee-User: "+username,
                        HttpStatus.OK
                );
            }
            catch (Exception e) {
                logger.severe("An error occurred when deleting Trainee-User: "+username);
                return new ResponseEntity<>(
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }
        else {
            logger.severe("No Trainee-User by that username: "+username);
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
    // Should support a login by username and password
    public Optional<Trainee> traineesByUsername(String username){
        List<Trainee> trainees = traineeRepository.findAll();
        for(Trainee trainee : trainees){
            if(trainee.getUser().getUsername().equals(username)){
                return Optional.of(trainee);
            }
        }
        return Optional.empty();
    }
}
