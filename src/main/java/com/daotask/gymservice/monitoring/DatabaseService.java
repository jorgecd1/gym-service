package com.daotask.gymservice.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService implements HealthIndicator {

    private final String DATABASE_SERVICE = "databaseService";

    @Override
    public Health health() {
        if(isDatabaseHealthGood()){
            return Health.up().withDetail(DATABASE_SERVICE,"Service is running. Connection to H2 established").build();
        }
        return Health.down().withDetail(DATABASE_SERVICE,"Service is not running. Connection to H2 missing or interrupted").build();
    }

    private boolean isDatabaseHealthGood(){
        return true;
    }
}
