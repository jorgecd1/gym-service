package com.daotask.gymservice.controller;

import com.daotask.gymservice.model.Trainee;
import com.daotask.gymservice.repos.TraineeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TraineeController {

    @Autowired
    private TraineeRepo traineeRepo;

    @GetMapping("/getAllTrainees")
    public ResponseEntity<List<Trainee>> getAllTrainees(){
        try
        {
            List<Trainee> traineeList = new ArrayList<>();
            traineeList.addAll(traineeRepo.findAll());

            if(traineeList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(traineeList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getTrainee/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id){

        Optional<Trainee> trainee = traineeRepo.findById(id);

        if(trainee.isPresent())
        {
            return new ResponseEntity<>(trainee.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addTrainee")
    public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee trainee){
        Trainee newTrainee = traineeRepo.save(trainee);

        return new ResponseEntity<>(newTrainee, HttpStatus.OK);
    }
    @PostMapping("/updateTrainee/{id}")
    public void updateTraineeById(@PathVariable Long id, @RequestBody Trainee newTraineeData){
        Optional<Trainee> oldTraineeData = traineeRepo.findById(id);

        if(oldTraineeData.isPresent()){
            Trainee updatedTraineeData = oldTraineeData.get();

            updatedTraineeData.setFirstName(newTraineeData.getFirstName());
            updatedTraineeData.setLastName(newTraineeData.getLastName());
        }
    }
    @DeleteMapping
    public void deleteTraineeById(){

    }
}
