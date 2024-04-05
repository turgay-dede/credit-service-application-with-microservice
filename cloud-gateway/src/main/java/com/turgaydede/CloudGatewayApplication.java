package com.turgaydede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class,args);
    }


//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(
//                id -> new Resilience4JConfigBuilder(id)
//                        .circuitBreakerConfig(
//                                CircuitBreakerConfig.ofDefaults()
//
//                        ).build()
//        );
//    }
}
