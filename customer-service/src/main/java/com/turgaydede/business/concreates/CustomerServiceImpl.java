package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CustomerService;
import com.turgaydede.constants.Messages;
import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.exceptions.CustomerNotFoundException;
import com.turgaydede.repositories.CustomerRepository;
import com.turgaydede.util.converter.CustomerDtoConverter;
import com.turgaydede.util.result.DataResult;
import com.turgaydede.util.result.SuccessDataResult;
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
    public DataResult<CustomerDto> add(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .identityNumber(customerDto.getIdentityNumber())
                .monthlyIncome(customerDto.getMonthlyIncome())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        return new SuccessDataResult<>(customerDtoConverter.convert(customer), Messages.ADDED);
    }

    @Override
    public DataResult<CustomerDto> delete(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
        return new SuccessDataResult<>(customerDtoConverter.convert(customer),Messages.DELETED);
    }

    @Override
    public DataResult<CustomerDto> update(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .fullName(customerDto.getFullName())
                .identityNumber(customerDto.getIdentityNumber())
                .monthlyIncome(customerDto.getMonthlyIncome())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        customerRepository.save(customer);
        return new SuccessDataResult<>(customerDtoConverter.convert(customer),Messages.UPDATED);
    }

    @Override
    public DataResult<List<CustomerDto>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return new SuccessDataResult<>(customers.stream().map(customerDtoConverter::convert).collect(Collectors.toList()),Messages.LISTED);
    }

    @Override
    public DataResult<CustomerDto> getByCustomerForIdentityNumber(String identityNumber) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(CustomerNotFoundException::new);
        return new SuccessDataResult<>(customerDtoConverter.convert(customer),Messages.CUSTOMER_FOR_IDENTITY_NUMBER);
    }
}
