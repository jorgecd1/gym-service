package com.daotask.gymservice.service;

import com.daotask.gymservice.dao.TrainerRepository;
import com.daotask.gymservice.dao.TrainingRepository;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.microservice.TrainerPayloadDto;
import com.daotask.gymservice.microservice.TrainerWorkloadDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
@FeignClient("microservice-service")
public class MicroserviceService {

    private String serviceUrl ="http://microservice-service"

    private TrainerRepository trainerRepository;
    private TrainingRepository trainingRepository;
    private UserRepository userRepository;

    // get action sender
    public TrainerPayloadDto getActionCall(TrainerWorkloadDto data){
        return null;
    }
}
