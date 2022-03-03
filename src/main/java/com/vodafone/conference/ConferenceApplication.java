package com.vodafone.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConferenceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConferenceApplication.class, args);
    }

}