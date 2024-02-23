package com.daotask.gymservice.controller;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.dao.TraineeRepository;
import com.daotask.gymservice.dao.TrainerRepository;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.dto.NewTraineeDTO;
import com.daotask.gymservice.dto.ToggleActiveStatusDTO;
import com.daotask.gymservice.dto.UpdateTraineeDTO;
import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.service.TraineeService;
import com.daotask.gymservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    TraineeService traineeService;
    UserService userService;

    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());

    public  TraineeController(
            TraineeService traineeService,
            UserService userService
    ){
        this.traineeService = traineeService;
        this.userService = userService;
    }
    // Should support create, read, update and delete operations
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody NewTraineeDTO traineeDTO){
        logger.info("Call to API, create new Trainee");
        return traineeService.add(traineeDTO);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable Long id){
        logger.info("Call to API, get Trainee");
        return traineeService.get(id);
    }
    @GetMapping("/get-username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username){
        logger.info("Call to API, get Trainee by Username");
        return traineeService.getByUsername(username);
    }
    @GetMapping("/get/not-assigned/{username}")
    public ResponseEntity<String> getNotAssigned(@PathVariable String username){
        return traineeService.getNotAssignedTrainers(username);
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<String>> getAll(){
        return traineeService.getAll();
    }
    @PatchMapping("/toggle-active")
    public ResponseEntity<String> toggleActiveState(@RequestBody ToggleActiveStatusDTO statusDTO){
        return traineeService.toggleStatus(statusDTO);
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UpdateTraineeDTO traineeDTO){
        return traineeService.update(traineeDTO);
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> delete(@PathVariable String username){
        return traineeService.delete(username);
    }
}
