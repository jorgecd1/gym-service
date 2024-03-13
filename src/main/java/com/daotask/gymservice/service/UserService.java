package com.daotask.gymservice.service;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.dto.LoginDTO;
import com.daotask.gymservice.dto.NewLoginDTO;
import com.daotask.gymservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@FeignClient("user-service")
public class UserService {

    UserRepository userRepository;

    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());
    private String serviceUrl ="http://user-service"
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    // Should support create, read, update and delete operations
    public ResponseEntity<String> changeLogin(NewLoginDTO newLoginDTO){

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword(newLoginDTO.getOldPassword());
        loginDTO.setUsername(newLoginDTO.getUsername());

        ResponseEntity<String> validateLogin = login(loginDTO);
        if(validateLogin.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User changed password");
            return new ResponseEntity<>(
                    "Changed password",
                    HttpStatus.OK
            );
        }
        else {
            logger.warning("Incorrect login, password unchanged");
            return new ResponseEntity<>(
                    HttpStatus.UNAUTHORIZED
            );
        }
    }
    public ResponseEntity<String> login(LoginDTO loginDTO){
        Optional<User> user = findByUsername(loginDTO.getUsername());
        if(user.isPresent()){
            if(loginDTO.getPassword().equals(user.get().getPassword())){
                logger.info("User is now logged in: "+user.get().getUsername());
                return new ResponseEntity<>(
                        "User is now logged in: " + user.get().getUsername(),
                        HttpStatus.OK
                );
            }
            else {
                logger.info("Failed login attempt for: "+user.get().getUsername());
                return new ResponseEntity<>(
                        HttpStatus.UNAUTHORIZED
                );
            }
        }
        else {
            logger.info("No such user with username: "+loginDTO.getUsername());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public Optional<User> findByUsername(String username){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            logger.info("No such user with username: "+username);
            return Optional.empty();
        }
        else {
            for(User user : users){
                if(user.getUsername().equals(username)){
                    logger.info("Found user: "+username);
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        }
    }
    // Generators
    public String usernameGenerator(String firstName, String lastName){
        logger.info("Fetching usernames to check uniqueness");
        int counter = 0;
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(
                    user.getFirstName().equals(firstName)
                    &&
                    user.getLastName().equals(lastName)
            ){
                counter+=1;
            }
        }
        String username = firstName+"."+lastName;
        if(counter == 0){
            return username;
        }
        else {
            return username+counter;
        }
    }
}
