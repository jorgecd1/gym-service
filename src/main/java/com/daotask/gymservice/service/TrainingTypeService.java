package com.daotask.gymservice.service;

import com.daotask.gymservice.GymServiceApplication;
import com.daotask.gymservice.dao.TrainingRepository;
import com.daotask.gymservice.dao.TrainingTypeRepository;
import com.daotask.gymservice.entities.Training;
import com.daotask.gymservice.entities.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@FeignClient("trainingtype-service")
public class TrainingTypeService {

    TrainingTypeRepository trainingTypeRepository;
    Logger logger = Logger.getLogger(GymServiceApplication.class.getName());
    private String serviceUrl ="http://trainingtype-service"
    @Autowired
    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository){
        this.trainingTypeRepository = trainingTypeRepository;
    }
    // Should support create and get training type
    // Create
    public ResponseEntity<String> add(String name){
        try{
            if(Objects.isNull(name)){
                logger.warning("Error when handling TrainingType.add, Name was null");
                return new ResponseEntity<>(
                        "Training-Type name cannot be null",
                        HttpStatus.NOT_ACCEPTABLE);
            }

            TrainingType trainingType = new TrainingType();
            trainingType.setName(name);

            trainingTypeRepository.save(trainingType);
            logger.info("Successfully added new TrainingType");
            return new ResponseEntity<>(
                    "New TrainingType added: {"
                            + trainingType.toString()
                            +"}",
                    HttpStatus.OK
            );
        }
        catch (Exception e){
            logger.severe(
                    "An unknown error happened when trying: TrainingType.add"
            );
            return new ResponseEntity<>(
                    "Severe error occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    // Get
    public ResponseEntity<String> get(Long id){
        try{
            Optional<TrainingType> trainingType = trainingTypeRepository.findById(id);
            if(trainingType.isPresent()){
                logger.info("Success retrieving TrainingType by Id");
                return new ResponseEntity<>(
                        trainingType.get().toString(),
                        HttpStatus.OK
                );
            }
            else {
                logger.info(
                        "No TrainingType was found"
                );
                return new ResponseEntity<>(
                        "No TrainingType with such id, id#"+id,
                        HttpStatus.NOT_FOUND
                );
            }
        }
        catch (Exception e){
            logger.severe("An unknown error happened when trying: TrainingType.get");
            return new ResponseEntity<>(
                    "Severe error occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    public ResponseEntity<List<TrainingType>> getAll(){
        try {
            List<TrainingType> trainingTypes = trainingTypeRepository.findAll();
            if(trainingTypes.isEmpty()){
                logger.info("No TrainingTypes are registered or none were found");
                return new ResponseEntity<>(
                        HttpStatus.NO_CONTENT
                );
            }
            else {
                logger.info("Successfully retrieved TrainingType List");
                return new ResponseEntity<>(
                        trainingTypes,
                        HttpStatus.OK
                );
            }
        }
        catch (Exception e){
            logger.severe("An unknown error happened when trying: TrainingType.getAll");
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    public Optional<TrainingType> trainingTypeByUsername(String name){
        List<TrainingType> trainingTypes = trainingTypeRepository.findAll();
        for(TrainingType trainingType : trainingTypes){
            logger.info("Found TrainingType: "+name);
            return Optional.of(trainingType);
        }
        logger.info("TrainingType not found: "+name);
        return Optional.empty();
    }
}
