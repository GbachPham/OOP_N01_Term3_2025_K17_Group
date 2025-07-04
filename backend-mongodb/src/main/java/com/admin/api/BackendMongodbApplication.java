package com.admin.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BackendMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendMongodbApplication.class, args);
    }

} 