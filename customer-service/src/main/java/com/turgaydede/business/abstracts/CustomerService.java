package com.turgaydede.business.abstracts;

import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.util.result.DataResult;

import java.util.List;

public interface CustomerService {
    DataResult<CustomerDto> getByCustomerForIdentityNumber(String identityNumber);
    DataResult<CustomerDto> add(CustomerDto customerDto);
    DataResult<CustomerDto> delete(int customerId);
    DataResult<CustomerDto> update(CustomerDto customerDto);
    DataResult<List<CustomerDto>> getAll();
}
