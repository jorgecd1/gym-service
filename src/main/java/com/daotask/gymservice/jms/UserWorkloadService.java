package com.epam.task.gymtasksecurity.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserWorkloadService {
    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String USER_WORKLOAD_QUEUE = "user.workload.queue";
    public void send(TraineeWorkload traineeWorkload){
        jmsTemplate.convertAndSend(USER_WORKLOAD_QUEUE, traineeWorkload);
    }
}
