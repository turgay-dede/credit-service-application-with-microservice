package com.turgaydede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.turgaydede.*","org.springdoc"})
public class KeycloakApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeycloakApplication.class, args);
    }
}