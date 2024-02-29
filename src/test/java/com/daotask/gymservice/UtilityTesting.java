package com.daotask.gymservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UtilityTesting {

    Utility utility = new Utility();

    @Test
    void generatedPasswordHasCorrectLength() {
        String generatedPassword = utility.generatePassword();

        assertEquals(10, generatedPassword.length());
    }

    @Test
    void generatedPasswordContainsOnlyAlphanumericCharacters() {
        String generatedPassword = utility.generatePassword();

        assertTrue(generatedPassword.matches("[A-Za-z0-9]+"));
    }
}
