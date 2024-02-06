package com.daotask.gymservice.service;

import com.daotask.gymservice.ClassPackages.DataGenerator;
import com.daotask.gymservice.dao.TraineeDAO;
import com.daotask.gymservice.entities.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {

    @Autowired
    private TraineeDAO dao;
    private DataGenerator dataGenerator;

    // GET DAO
    public TraineeDAO getTraineeDAO(){
        return dao;
    }

    // DELETE DATA
    public ResponseEntity<Trainee> deleteTrainee(Long id){
        try
        {
            Optional<Trainee> trainee = dao.findById(id);

            if(trainee.isPresent())
            {
                Trainee t = trainee.get();

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
    public ResponseEntity<Trainee> addTrainee(Trainee trainee){
        try
        {
            trainee.setPassword(dataGenerator.generatePassword());
            trainee.setUsername(dataGenerator.generateUsername(trainee.getFirstName(),trainee.getLastName()));

            dao.save(trainee);
            return new ResponseEntity<>(trainee, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Trainee> updateTrainee(Long id, Trainee newTrainee){
        Optional<Trainee> oldTrainee = dao.findById(id);

        if(oldTrainee.isPresent())
        {
            Trainee updateTrainee = oldTrainee.get();

            updateTrainee.setFirstName(newTrainee.getFirstName());
            updateTrainee.setLastName(newTrainee.getLastName());
            updateTrainee.setUsername(newTrainee.getUsername());
            updateTrainee.setPassword(newTrainee.getPassword());
            updateTrainee.setActive(newTrainee.isActive());

            updateTrainee.setAddress(newTrainee.getAddress());
            updateTrainee.setDateOfBirth(newTrainee.getDateOfBirth());

            Trainee objTrainee = dao.save(updateTrainee);
            return new ResponseEntity<>(objTrainee, HttpStatus.OK);
        }
        return new ResponseEntity<>(newTrainee, HttpStatus.NOT_FOUND);
    }

    // GET DATA
    public ResponseEntity<List<Trainee>> getAllTrainees(){
        try
        {
            List<Trainee> trainees = dao.findAll();

            if(trainees.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(trainees, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Trainee> getTraineeById(Long id){
        Optional<Trainee> trainee = dao.findById(id);

        if(trainee.isPresent())
        {
            return new ResponseEntity<>(trainee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
