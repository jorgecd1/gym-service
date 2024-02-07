package com.daotask.gymservice.controllers;

import com.daotask.gymservice.entities.Training;
import com.daotask.gymservice.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainingController {

    @Autowired
    private TrainingService service;

    // POST DATA
    @PostMapping("/addTraining")
    public ResponseEntity<Training> addTraining(@RequestBody Training training){
        return service.addTraining(training);
    }
    // GET DATA
    @GetMapping("/getTrainings")
    public ResponseEntity<List<Training>> getTrainings(){
        return service.getAllTrainings();
    }
    @GetMapping("/getTraining/{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id){
        return service.getTrainingById(id);
    }
}
