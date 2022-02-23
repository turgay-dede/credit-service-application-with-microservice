package com.turgaydede.business.abstracts;

import com.turgaydede.dtos.CustomerDto;
import com.turgaydede.entities.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto getByCustomerForIdentityNumber(String identityNumber);
    CustomerDto add(CustomerDto customerDto);
    CustomerDto delete(int customerId);
    CustomerDto update(CustomerDto customerDto);
    List<CustomerDto> getAll();
}
