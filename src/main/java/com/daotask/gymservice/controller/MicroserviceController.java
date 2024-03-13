package com.daotask.gymservice.controller;

import com.daotask.gymservice.microservice.TrainerPayloadDto;
import com.daotask.gymservice.microservice.TrainerWorkloadDto;
import com.daotask.gymservice.service.TraineeService;
import com.daotask.gymservice.service.TrainerService;
import com.daotask.gymservice.service.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/microservice/")
public class MicroserviceController {
    private final TrainingService trainingService;

    public MicroserviceController(
            TrainingService trainingService
    ){
        this.trainingService = trainingService;
    }

    @GetMapping("/call")
    public ResponseEntity<TrainerPayloadDto> getActionCall(@RequestBody TrainerWorkloadDto data){



        return null;
    }
}

