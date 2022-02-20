package com.turgaydede.business.abstracts;

import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.entities.dtos.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    CustomerDto getByCustomerForIdentityNumber(String identityNumber);
    CustomerDto add(CustomerDto customerDto);
    CustomerDto delete(int customerId);
    CustomerDto update(CustomerUpdateRequest customerUpdateRequest);
    List<CustomerDto> getAll();
}
