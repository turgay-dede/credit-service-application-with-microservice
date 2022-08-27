package com.turgaydede.business.concreates;

import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void add() {
        Customer customer = generateCustomer();
        CustomerDto customerDto = generateCustomerDto();

        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDto result = customerServiceImpl.add(customerDto).getData();
        assertEquals(result, customerDto);
    }

    @Test
    void delete() {
        Customer customer = generateCustomer();

        customerRepository.delete(customer);

        verify(customerRepository, Mockito.times(1)).delete(customer);
    }

    @Test
    void update() {
        CustomerDto customerDto = generateCustomerDto();
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .identityNumber(customerDto.getIdentityNumber())
                .monthlyIncome(customerDto.getMonthlyIncome())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();

        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDto result = customerServiceImpl.update(customerDto).getData();
        assertNotEquals(result, customerDto);
        verify(customerRepository).save(Mockito.any(Customer.class));
    }

    @Test
    void getAll() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = generateCustomer();
        Customer customer2 = generateCustomer();
        customerList.add(customer1);
        customerList.add(customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDto> result = customerServiceImpl.getAll().getData();
        assertEquals(customerList.size(), result.size());
    }

    @Test
    void getByCustomerForIdentityNumber() {
        String identityNumber = "20000000000";
        CustomerDto customerDto = generateCustomerDto();
        Customer customer = Customer.builder()
                .id(1)
                .fullName("Turgay Dede")
                .identityNumber("20000000000")
                .monthlyIncome(8000)
                .phoneNumber("5400000000")
                .build();

        when(customerRepository.findByIdentityNumber(identityNumber)).thenReturn(Optional.of(customer));

        CustomerDto result = customerServiceImpl.getByCustomerForIdentityNumber(customer.getIdentityNumber()).getData();

        assertEquals(result, customerDto);
        verify(customerRepository).findByIdentityNumber(identityNumber);
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