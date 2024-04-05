package com.turgaydede.api.controllers;

import com.turgaydede.business.abstracts.CustomerService;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.util.result.DataResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('admin_auth_client')")
    public ResponseEntity<DataResult<CustomerDto>> add(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.add(customerDto));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('admin_auth_client')")
    public ResponseEntity<DataResult<CustomerDto>> delete(@RequestParam int customerId) {
        return ResponseEntity.ok(customerService.delete(customerId));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('admin_auth_client')")
    public ResponseEntity<DataResult<CustomerDto>> update(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.update(customerDto));
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public ResponseEntity<DataResult<List<CustomerDto>>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/get/customer/identity-number")
    @PreAuthorize("hasRole('admin_auth_client')")
    public ResponseEntity<DataResult<CustomerDto>> getByCustomerForIdentityNumber(@RequestParam String identityNumber) {
        return ResponseEntity.ok(customerService.getByCustomerForIdentityNumber(identityNumber));
    }
}