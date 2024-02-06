package com.daotask.gymservice.controllers;

import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {

    @Autowired
    private TrainerService service;

    // DELETE DATA
    @DeleteMapping("/deleteTrainer/{id}")
    public ResponseEntity<Trainer> deleteTrainer(@PathVariable Long id){
        return service.deleteTrainer(id);
    }

    // POST DATA
    @PostMapping("/addTrainer")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer){
        return service.addTrainer(trainer);
    }
    @PutMapping("/updateTrainer/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer newTrainer){
        return service.updateTrainer(id, newTrainer);
    }

    // GET DATA
    @GetMapping("/getTrainers")
    public ResponseEntity<List<Trainer>> getTrainers(){
        return  service.getAllTrainers();
    }
    @GetMapping("/getTrainer/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id){
        return service.getTrainerById(id);
    }
}
