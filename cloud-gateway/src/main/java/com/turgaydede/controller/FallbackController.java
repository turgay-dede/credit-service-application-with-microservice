package com.turgaydede.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/keycloakServiceFallBack")
    public String orderServiceFallback() {
        return "Keycloak Service is down!";
    }

    @GetMapping("/customerServiceFallBack")
    public String paymentServiceFallback() {
        return "Customer Service is down!";
    }

}