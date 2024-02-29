package com.daotask.gymservice.controller;

import com.daotask.gymservice.dto.NewTrainingDTO;
import com.daotask.gymservice.service.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")
public class TrainingController {

    TrainingService trainingService;

    public TrainingController(
            TrainingService trainingService
    ){
        this.trainingService = trainingService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(NewTrainingDTO trainingDTO){
        return trainingService.add(trainingDTO);
    }
}
