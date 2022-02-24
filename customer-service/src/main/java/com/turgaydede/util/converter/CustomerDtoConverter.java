package com.turgaydede.util.converter;

import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDtoConverter {
    public CustomerDto convert(Customer customer){
          return new CustomerDto.Builder()
                  .id(customer.getId())
                  .fullName(customer.getFullName())
                  .identityNumber(customer.getIdentityNumber())
                  .monthlyIncome(customer.getMonthlyIncome())
                  .phoneNumber(customer.getPhoneNumber())
                  .build();
    }
}
