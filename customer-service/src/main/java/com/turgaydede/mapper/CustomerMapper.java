package com.turgaydede.mapper;

import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto getDto(Customer customer);
    Customer getObj(CustomerDto customerDto);
}
