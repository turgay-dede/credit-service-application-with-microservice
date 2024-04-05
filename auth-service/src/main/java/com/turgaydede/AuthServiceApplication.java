package com.turgaydede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.turgaydede.*","org.springdoc"})
@EnableDiscoveryClient
public class AuthServiceApplication  {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}