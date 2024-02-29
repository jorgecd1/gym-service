package com.daotask.gymservice.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements HealthIndicator {

    private final String LOGGER_SERVICE = "loggerService";

    @Override
    public Health health(){
        if(isLoggerServiceGood()){
            return Health.up().withDetail(LOGGER_SERVICE,"Logging service is running").build();
        }
        return Health.down().withDetail(LOGGER_SERVICE,"Logging service interrupted or missing").build();
    }

    private boolean isLoggerServiceGood(){
        return true;
    }
}
