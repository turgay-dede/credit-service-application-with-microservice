package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CustomerService;
import com.turgaydede.dtos.CustomerDto;
import com.turgaydede.entities.Customer;
import com.turgaydede.exceptions.CustomerNotFoundException;
import com.turgaydede.repositories.CustomerRepository;
import com.turgaydede.util.converter.CustomerDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    @Override
    public CustomerDto add(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .identityNumber(customerDto.getIdentityNumber())
                .monthlyIncome(customerDto.getMonthlyIncome())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        return customerDtoConverter.convert(customer);
    }

    @Override
    public CustomerDto delete(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
        return customerDtoConverter.convert(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .identityNumber(customerDto.getIdentityNumber())
                .monthlyIncome(customerDto.getMonthlyIncome())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        customerRepository.save(customer);
        return customerDtoConverter.convert(customer);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getByCustomerForIdentityNumber(String identityNumber) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(CustomerNotFoundException::new);
        return customerDtoConverter.convert(customer);
    }
}
