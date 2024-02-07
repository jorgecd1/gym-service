package com.daotask.gymservice.service;

import com.daotask.gymservice.dao.TrainingDAO;
import com.daotask.gymservice.dao.TrainingTypeDAO;
import com.daotask.gymservice.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingDAO dao;

    // FETCH DAO's
    private TrainingTypeDAO trainingTypeDAO;

    // GET DAO
    public TrainingDAO getTrainingDAO(){
        return dao;
    }

    // POST DATA
    public ResponseEntity<Training> addTraining(Training training){
        try
        {
            dao.save(training);
            return new ResponseEntity<>(training, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET DATA
    public ResponseEntity<List<Training>> getAllTrainings(){
        try
        {
            List<Training> trainings = dao.findAll();

            if(trainings.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(trainings, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id){
        Optional<Training> training = dao.findById(id);

        if(training.isPresent())
        {
            return new ResponseEntity<>(training.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
