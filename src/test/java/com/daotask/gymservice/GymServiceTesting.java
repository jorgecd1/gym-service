package com.daotask.gymservice;

import com.daotask.gymservice.controller.TraineeController;
import com.daotask.gymservice.dao.TraineeRepository;
import com.daotask.gymservice.dao.UserRepository;
import com.daotask.gymservice.dto.NewTraineeDTO;
import com.daotask.gymservice.entities.User;
import com.daotask.gymservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

public class GymServiceTesting {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TraineeController traineeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void add_SuccessfulCreation_ReturnsOkResponse() {
        NewTraineeDTO traineeDTO = new NewTraineeDTO();
        traineeDTO.setFirstName("John");
        traineeDTO.setLastName("Doe");
        traineeDTO.setAddress("123 Main St");
        traineeDTO.setDateOfBirth(Date.valueOf("1990-05-05"));

        Utility utilityMock = mock(Utility.class);
        when(utilityMock.generatePassword()).thenReturn("mockedPassword");

        when(userService.usernameGenerator("John", "Doe")).thenReturn("john.doe");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(traineeRepository.save(any(Trainee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<String> response = traineeController.add(traineeDTO);

        verify(userRepository, times(1)).save(any(User.class));
        verify(traineeRepository, times(1)).save(any(Trainee.class));
    }

    @Test
    void getByUsername_TrainerFound_ReturnsOkResponse() {
        // Arrange
        String username = "john.doe";
        Trainer mockedTrainer = new Trainer();
        mockedTrainer.setId(1L);
        mockedTrainer.setTrainingType(new TrainingType("Mocked Training"));
        mockedTrainer.setTrainees(/* initialize your trainees */);

        User mockedUser = new User();
        mockedUser.setUsername(username);
        mockedUser.setFirstName("John");
        mockedUser.setLastName("Doe");
        mockedUser.setIsActive(true);

        mockedTrainer.setUser(mockedUser);

        when(trainerService.trainerByUsername(username)).thenReturn(Optional.of(mockedTrainer));

        ResponseEntity<String> response = trainerController.getByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(trainerService, times(1)).trainerByUsername(username);
    }

    @Test
    void getByUsername_TrainerNotFound_ReturnsNotFoundResponse() {
        // Arrange
        String username = "nonexistent.user";

        when(trainerService.trainerByUsername(username)).thenReturn(Optional.empty());

        ResponseEntity<String> response = trainerController.getByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(trainerService, times(1)).trainerByUsername(username);
    }
}
