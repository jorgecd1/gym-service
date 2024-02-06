package com.daotask.gymservice.service;

import com.daotask.gymservice.ClassPackages.DataGenerator;
import com.daotask.gymservice.dao.TrainerDAO;
import com.daotask.gymservice.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerDAO dao;
    private DataGenerator dataGenerator;

    // GET DAO
    public TrainerDAO getTrainerDao(){
        return dao;
    }

    // DELETE DATA
    public ResponseEntity<Trainer> deleteTrainer(Long id){
        try
        {
            Optional<Trainer> trainer = dao.findById(id);

            if(trainer.isPresent())
            {
                Trainer t = trainer.get();

                dao.deleteById(id);
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST DATA
    public ResponseEntity<Trainer> addTrainer(Trainer trainer){
        try
        {
            trainer.setUsername(dataGenerator.generateUsername(trainer.getFirstName(),trainer.getLastName()));
            trainer.setPassword(dataGenerator.generatePassword());

            dao.save(trainer);
            return new ResponseEntity<>(trainer, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Trainer> updateTrainer(Long id, Trainer newTrainer){
        Optional<Trainer> oldTrainer = dao.findById(id);

        if(oldTrainer.isPresent()){
            Trainer updateTrainer = oldTrainer.get();

            updateTrainer.setFirstName(newTrainer.getFirstName());
            updateTrainer.setLastName(newTrainer.getLastName());
            updateTrainer.setUsername(newTrainer.getUsername());
            updateTrainer.setPassword(newTrainer.getPassword());
            updateTrainer.setActive(newTrainer.isActive());

            updateTrainer.setSpecialization(newTrainer.getSpecialization());

            Trainer objTrainer = dao.save(updateTrainer);
            return new ResponseEntity<>(objTrainer, HttpStatus.OK);
        }
        return new ResponseEntity<>(newTrainer, HttpStatus.NOT_FOUND);
    }

    // GET DATA
    public ResponseEntity<List<Trainer>> getAllTrainers(){
        try
        {
            List<Trainer> trainers = dao.findAll();

            if(trainers.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(trainers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Trainer> getTrainerById(Long id){
        Optional<Trainer> trainer = dao.findById(id);

        if(trainer.isPresent())
        {
            return new ResponseEntity<>(trainer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
