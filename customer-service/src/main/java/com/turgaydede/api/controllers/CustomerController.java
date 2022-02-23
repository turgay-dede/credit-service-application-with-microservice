package com.turgaydede.api.controllers;

import com.turgaydede.business.abstracts.CustomerService;
import com.turgaydede.dtos.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/add")
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.add(customerDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CustomerDto> delete(@RequestParam int customerId) {
        return ResponseEntity.ok(customerService.delete(customerId));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.update(customerDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/get/customer/identity-number")
    public ResponseEntity<CustomerDto> getByCustomerForIdentityNumber(@RequestParam String identityNumber) {
        return ResponseEntity.ok(customerService.getByCustomerForIdentityNumber(identityNumber));
    }
}