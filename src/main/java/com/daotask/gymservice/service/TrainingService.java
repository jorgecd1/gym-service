package com.daotask.gymservice.service;

import com.daotask.gymservice.ClassPackages.DataGenerator;
import com.daotask.gymservice.dao.TraineeDAO;
import com.daotask.gymservice.dao.TrainerDAO;
import com.daotask.gymservice.dao.TrainingDAO;
import com.daotask.gymservice.dao.TrainingTypeDAO;
import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.entities.Training;
import com.daotask.gymservice.entities.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingDAO dao;

    // FETCH DAO's
    private TraineeDAO traineeDAO;
    private TrainerDAO trainerDAO;
    private TrainingTypeDAO trainingTypeDAO;

    // GET DAO
    public TrainingDAO getTrainingDAO(){
        return dao;
    }
    private DataGenerator dataGenerator;

    // DELETE DATA
    public ResponseEntity<Training> deleteTraining(Long id){
        try
        {
            Optional<Training> training = dao.findById(id);

            if(training.isPresent()){
                Training t = training.get();

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
    public ResponseEntity<Training> addTraining(Training training){
        try
        {
            Optional<Trainee> trainee = traineeDAO.findById(training.getTraineeId());
            Optional<Trainer> trainer = trainerDAO.findById(training.getTrainerId());
            Optional<TrainingType> trainingType = trainingTypeDAO.findById(training.getTrainingTypeId());

            List<Optional> optionalList = List.of(trainee,trainer,trainingType);
            boolean validateOptionals = dataGenerator.validateOptional(optionalList);

            if(validateOptionals)
            {
                TrainingType trainingTypeData = trainingType.get();

                training.setTrainingName(trainingTypeData.getTrainingTypeName());

                dao.save(training);
                return new ResponseEntity<>(training, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Training> updateTraining(Long id, Training newTraining){
        Optional<Training> oldTraining = dao.findById(id);

        if(oldTraining.isPresent())
        {
            Training updateTraining = oldTraining.get();

            updateTraining.setTrainingDate(newTraining.getTrainingDate());
            updateTraining.setTrainingTime(newTraining.getTrainingTime());

            updateTraining.setTrainingName(newTraining.getTrainingName());

            updateTraining.setTrainerId(newTraining.getTrainerId());
            updateTraining.setTrainingTypeId(newTraining.getTrainingId());

            Training objTraining = dao.save(updateTraining);
            return new ResponseEntity<>(objTraining, HttpStatus.OK);
        }
        return new ResponseEntity<>(newTraining, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Training> getTrainingById(Long id){
        Optional<Training> training = dao.findById(id);

        if(training.isPresent())
        {
            return new ResponseEntity<>(training.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
