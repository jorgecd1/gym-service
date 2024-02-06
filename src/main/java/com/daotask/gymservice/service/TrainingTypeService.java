package com.daotask.gymservice.service;

import com.daotask.gymservice.dao.TrainingTypeDAO;
import com.daotask.gymservice.entities.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingTypeService {

    @Autowired
    private TrainingTypeDAO dao;

    // GET DAO
    public TrainingTypeDAO getTrainingTypeDAO(){
        return dao;
    }

    // DELETE DATA
    public ResponseEntity<TrainingType> deleteTrainingType(Long id){
        try
        {
            Optional<TrainingType> trainingType = dao.findById(id);

            if(trainingType.isPresent())
            {
                TrainingType t = trainingType.get();

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
    public ResponseEntity<TrainingType> addTrainingType(TrainingType trainingType){
        try
        {
            dao.save(trainingType);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<TrainingType> updateTraining(Long id, TrainingType newTrainingType){
        Optional<TrainingType> oldTraining = dao.findById(id);

        if(oldTraining.isPresent())
        {
            TrainingType updateTrainingType = oldTraining.get();

            updateTrainingType.setTrainingTypeName(newTrainingType.getTrainingTypeName());

            TrainingType objTrainingType = dao.save(updateTrainingType);
            return new ResponseEntity<>(objTrainingType, HttpStatus.OK);
        }
        return new ResponseEntity<>(newTrainingType, HttpStatus.NOT_FOUND);
    }

    // GET DATA
    public ResponseEntity<List<TrainingType>> getAllTrainingTypes(){
        try
        {
            List<TrainingType> trainingTypes = dao.findAll();

            if(trainingTypes.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(trainingTypes, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<TrainingType> getTrainingTypeById(Long id){
        Optional<TrainingType> trainingType = dao.findById(id);

        if(trainingType.isPresent())
        {
            return new ResponseEntity<>(trainingType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
