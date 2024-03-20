package com.epam.task.gymtasksecurity.jms;

import com.epam.task.gymtasksecurity.model.Trainee;
import com.epam.task.gymtasksecurity.model.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.util.Date;

@Data
public class TraineeWorkload {
    @JsonCreator
    public TraineeWorkload(
            User user,
            Trainee trainee
    ){
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = trainee.getDateOfBirth();
        this.address = trainee.getAddress();
        this.isActive = user.isActive();
    }

    private final String username;
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;
    private final String address;
    private final boolean isActive;
}
