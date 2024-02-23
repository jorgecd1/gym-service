package com.daotask.gymservice;

import com.daotask.gymservice.entities.Trainee;
import com.daotask.gymservice.entities.Trainer;
import com.daotask.gymservice.entities.Training;
import com.daotask.gymservice.entities.User;

import java.security.SecureRandom;
import java.util.Set;

public class Utility {

    public String generatePassword(){
         final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(10);

        for(int i = 0; i < 10; i++){
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC.length());
            char randomChar = ALPHANUMERIC.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
    public String trainerFormatter(Set<Trainer> trainers){
        StringBuilder stringBuilder = new StringBuilder("{");
        for(Trainer trainer : trainers){
            User user = trainer.getUser();
            stringBuilder
                    .append("{username: ")
                    .append(user.getUsername())
                    .append(",\nfirstName: ")
                    .append(user.getFirstName())
                    .append(",\nlastName: ")
                    .append(user.getLastName())
                    .append(",\nspecialization: ")
                    .append(trainer.getTrainingType().getName())
                    .append("},");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    public String traineeFormatter(Set<Training> trainings){
        StringBuilder stringBuilder = new StringBuilder("Trainees: {");
        for(Training training : trainings){
            Trainee trainee = training.getTrainee();
            User traineeUser = trainee.getUser();

            stringBuilder
                    .append("{username: ")
                    .append(traineeUser.getUsername())
                    .append(",\n").append("firstName: ")
                    .append(traineeUser.getFirstName())
                    .append(",\n").append("lastName: ")
                    .append(traineeUser.getLastName())
                    .append("}\n");
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
    public String trainingFormatter(Set<Training> trainings){
        StringBuilder stringBuilder = new StringBuilder("Trainings: {");
        for(Training training : trainings){
            Trainer trainer = training.getTrainer();
            User trainerUser = trainer.getUser();

            stringBuilder
                    .append("{username: ")
                    .append(trainerUser.getUsername())
                    .append(", firstName: ")
                    .append(trainerUser.getFirstName())
                    .append(", lastName: ")
                    .append(trainerUser.getLastName())
                    .append(", trainingSpecialization: ")
                    .append(trainer.getTrainingType().getName())
                    .append("} \n")
            ;
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
