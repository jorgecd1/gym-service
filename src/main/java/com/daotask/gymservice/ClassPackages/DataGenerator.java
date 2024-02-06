package com.daotask.gymservice.ClassPackages;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

public class DataGenerator {
    // GENERATE METHODS
    public String generatePassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(10);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
    public String generateUsername(String firstName, String lastName){
        return firstName+"."+lastName;
    }

    public boolean validateOptional(List<Optional> optionalList){
        for(Optional o : optionalList)
        {
            if(o.isEmpty())
            {
                return false;
            }
        }
        return true;
    }
}
