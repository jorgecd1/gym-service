package com.daotask.gymservice.security.service;

import com.daotask.gymservice.security.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {
    private static final String USER_USERNAME = "myUser";
    private static final String ADMIN_USER = "myAdmin";

    public Optional<UserEntity> findByEmail(String email) {
        // All of this code would come from the database, but for simplicity of this example
        // Let's just fake it

        if (USER_USERNAME.equalsIgnoreCase(email)) {
            var user = new UserEntity();
            user.setId(1L);
            user.setUsername(USER_USERNAME);
            user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); // test
            user.setRole("ROLE_ADMIN");
            user.setExtraInfo("My nice admin user");
            return Optional.of(user);
        } else if (ADMIN_USER.equalsIgnoreCase(email)) {
            var user = new UserEntity();
            user.setId(99L);
            user.setUsername(ADMIN_USER);
            user.setPassword("$2a$12$OBnerD3ZrnkqY/ofkaxune1jnpUscFhTGCcuVA9x5lgAGAtr6Bss2"); // test
            user.setRole("ROLE_USER");
            user.setExtraInfo("My nice simple user");
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
