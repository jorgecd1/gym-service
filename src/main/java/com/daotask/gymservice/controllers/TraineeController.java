package com.daotask.gymservice.controllers;

import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraineeController {

    @Autowired
    private TraineeService service;

    // DELETE DATA
    @DeleteMapping("/deleteTrainee/{id}")
    public ResponseEntity<Trainee> deleteTrainee(@PathVariable Long id){
        return service.deleteTrainee(id);
    }

    // POST DATA
    @PostMapping("/addTrainee")
    public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee trainee){
        return service.addTrainee(trainee);
    }
    @PutMapping("/updateTrainee/{id}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable Long id, @RequestBody Trainee newTrainee){
        return service.updateTrainee(id, newTrainee);
    }

    // GET DATA
    @GetMapping("/getTrainees")
    public ResponseEntity<List<Trainee>> getTrainees(){
        return  service.getAllTrainees();
    }
    @GetMapping("/getTrainee/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id){
        return service.getTraineeById(id);
    }
}
