package com.sacavix.cloudeventskafkaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CloudEventsKafkaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEventsKafkaSpringApplication.class, args);
    }

}
