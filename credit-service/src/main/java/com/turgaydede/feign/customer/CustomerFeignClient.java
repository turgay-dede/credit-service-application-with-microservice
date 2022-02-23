package com.turgaydede.feign.customer;

import com.turgaydede.entities.dtos.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "customer-service",url = "http://localhost:8081/rest/customers")
public interface CustomerFeignClient {
    @PostMapping("/add")
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto customerDto);
}
