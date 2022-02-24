package com.turgaydede.business.concreates;

import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.exceptions.CustomerNotFoundException;
import com.turgaydede.repositories.CustomerRepository;
import com.turgaydede.util.converter.CustomerDtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CustomerServiceImplTest {
    private CustomerServiceImpl customerServiceImpl;


    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;


    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerDtoConverter = Mockito.mock(CustomerDtoConverter.class);
        customerServiceImpl = new CustomerServiceImpl(customerRepository, customerDtoConverter);
    }

    @Test
    void add() {
        Customer customer = Customer.builder()
                .fullName("Turgay Dede")
                .identityNumber("20000000000")
                .monthlyIncome(8000)
                .phoneNumber("5400000000")
                .build();

        CustomerDto customerDto = new CustomerDto.Builder()
                .fullName("Turgay Dede")
                .identityNumber("20000000000")
                .monthlyIncome(8000)
                .phoneNumber("5400000000")
                .build();


        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);


        CustomerDto result = customerServiceImpl.add(customerDto).getData();
        Assertions.assertEquals(result, customerDto);

        Mockito.verify(customerDtoConverter).convert(customer);
    }

    @Test
    void delete() {
        int customerId = 105;

        Mockito.doThrow(new CustomerNotFoundException()).when(customerRepository).findById(customerId);

    }

}