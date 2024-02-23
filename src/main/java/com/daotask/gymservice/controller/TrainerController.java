package com.daotask.gymservice.controller;

import com.daotask.gymservice.dto.*;
import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.service.TraineeService;
import com.daotask.gymservice.service.TrainerService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    TrainerService trainerService;
    @Autowired
    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    // Should support create, read, update and delete operations
    // CREATE
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody NewTrainerDTO trainer){
        return trainerService.add(trainer);
    }
    // READ
    @GetMapping("/getAll")
    public ResponseEntity<String> getAll(){
        return new ResponseEntity<>("Call to get all", HttpStatus.OK);
    }
    @GetMapping("/get/{username}")
    public ResponseEntity<String> get(@PathVariable String username){
        return trainerService.getByUsername(username);
    }
    // UPDATE
    @PutMapping("/update/{username}")
    public ResponseEntity<String> update(@RequestBody UpdateTrainerDTO updateTrainerDTO){
        return trainerService.update(updateTrainerDTO);
    }
    @PatchMapping("/toggle-active")
    public ResponseEntity<String> toggleActiveState(@RequestBody ToggleActiveStatusDTO statusDTO){
        return trainerService.toggleStatus(statusDTO);
    }
    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>("Call to delete: "+id,HttpStatus.OK);
    }
    // Should support a login by username and password
}
