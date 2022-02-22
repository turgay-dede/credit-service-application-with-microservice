package com.turgaydede.business.abstracts;

import com.turgaydede.entities.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getByCustomerForIdentityNumber(String identityNumber);
    CustomerDto add(CustomerDto customerDto);
    CustomerDto delete(int customerId);
    CustomerDto update(CustomerDto customerDto);
    List<CustomerDto> getAll();
}
