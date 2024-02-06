package com.daotask.gymservice.controllers;

import com.daotask.gymservice.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

    @Autowired
    private TrainingService service;
}
