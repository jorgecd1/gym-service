package com.daotask.gymservice.controller;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.entities.TrainingType;
import com.daotask.gymservice.service.TrainingTypeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/training-type")
public class TrainingTypeController {
    // Should support create, get and get all

    TrainingTypeService trainingTypeService;
    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());

    public TrainingTypeController(TrainingTypeService trainingTypeService){
        this.trainingTypeService = trainingTypeService;
    }

    // Create
    @PostMapping("/add/{name}")
    public ResponseEntity<String> add(@PathVariable String name){
        logger.info("Call to create new TrainingType");
        return trainingTypeService.add(name);
    }
    // Read
    @GetMapping("/get/all")
    public ResponseEntity<List<TrainingType>> getAll(){
        logger.info("Call to get all TrainingType");
        return trainingTypeService.getAll();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable Long id){
        logger.info("Call to get a TrainingType by Id");
        return trainingTypeService.get(id);
    }
}
