package com.example.cotirovki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CotirovkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CotirovkiApplication.class, args);
    }

}
