package com.epam.task.gymtasksecurity.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WarehouseReceiverService {

    private static final Logger LOG = LoggerFactory.getLogger(WarehouseReceiverService.class);

    public void receive(TraineeWorkload traineeWorkload){
        LOG.info("Message received");
        LOG.info("Message values: "+traineeWorkload);
        // dead letter queue enabled at class level
    }
}
