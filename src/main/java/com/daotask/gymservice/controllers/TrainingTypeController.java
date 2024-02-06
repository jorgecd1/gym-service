package com.daotask.gymservice.controllers;

import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.TrainingType;
import com.daotask.gymservice.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainingTypeController {

    @Autowired
    private TrainingTypeService service;

    // DELETE DATA
    @DeleteMapping("/deleteTrainingType/{id}")
    public ResponseEntity<TrainingType> deleteTrainingType(@PathVariable Long id){
        return service.deleteTrainingType(id);
    }

    // POST DATA
    @PostMapping("/addTrainingType")
    public ResponseEntity<TrainingType> addTrainingType(@RequestBody TrainingType trainingType){
        return service.addTrainingType(trainingType);
    }
    @PutMapping("/updateTrainingType/{id}")
    public ResponseEntity<TrainingType> updateTrainingType(@PathVariable Long id, @RequestBody TrainingType trainingType){
        return service.updateTraining(id,trainingType);
    }

    // GET DATA
    @GetMapping("/getTrainingTypes")
    public ResponseEntity<List<TrainingType>> getTrainingTypes(){
        return service.getAllTrainingTypes();
    }
    @GetMapping("/getTrainingTypeById/{id}")
    public ResponseEntity<TrainingType> getTrainingTypeById(@PathVariable Long id){
        return service.getTrainingTypeById(id);
    }
}
