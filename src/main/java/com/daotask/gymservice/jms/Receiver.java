package com.epam.task.gymtasksecurity.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "order-queue")
    public void receiveMessage(String message){
        System.out.println("Order received is: "+message);
    }
}
