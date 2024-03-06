package com.daotask.gymservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
