package com.turgaydede.business.concreates;

import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.repositories.CustomerRepository;
import com.turgaydede.util.converter.CustomerDtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDtoConverter customerDtoConverter;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void add() {
        Customer customer = generateCustomer();
        CustomerDto customerDto = generateCustomerDto();


        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);


        CustomerDto result = customerServiceImpl.add(customerDto).getData();
        Assertions.assertEquals(result, customerDto);

        Mockito.verify(customerDtoConverter).convert(customer);
    }

    @Test
    void delete() {
        Customer customer = generateCustomer();

        customerRepository.delete(customer);

        Mockito.verify(customerRepository, Mockito.times(1)).delete(customer);
    }


    private Customer generateCustomer() {
        return Customer.builder()
                .id(1)
                .fullName("Turgay Dede")
                .identityNumber("20000000000")
                .monthlyIncome(8000)
                .phoneNumber("5400000000")
                .build();
    }

    private CustomerDto generateCustomerDto() {
        return new CustomerDto.Builder()
                .id(1)
                .fullName("Turgay Dede")
                .identityNumber("20000000000")
                .monthlyIncome(8000)
                .phoneNumber("5400000000")
                .build();
    }

}