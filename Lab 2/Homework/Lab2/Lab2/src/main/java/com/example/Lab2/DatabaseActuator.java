package com.example.Lab2;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@Endpoint(id = "database")
public class DatabaseActuator {

    private final Database properties;

    public DatabaseActuator(Database properties) {
        this.properties = properties;
    }

    @ReadOperation
    public Map<String, Object> databaseInfo() {
        return Map.of(
                "url", properties.getUrl(),
                "username", properties.getUsername(),
                "driver", properties.getDriverClassName()
        );
    }
}

