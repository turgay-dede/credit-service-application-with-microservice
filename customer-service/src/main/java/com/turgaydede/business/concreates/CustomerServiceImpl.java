package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CustomerService;
import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.exceptions.CustomerNotFoundException;
import com.turgaydede.mapper.CustomerMapper;
import com.turgaydede.repositories.CustomerRepository;
import com.turgaydede.util.constant.Messages;
import com.turgaydede.util.result.DataResult;
import com.turgaydede.util.result.SuccessDataResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
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


        customerRepository.save(customer);
        log.info(Messages.ADDED + " " + customer);
        return new SuccessDataResult<>(customerMapper.getDto(customer), Messages.ADDED);
    }

    @Override
    public DataResult<CustomerDto> delete(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
        log.info(Messages.DELETED + " " + customer);
        return new SuccessDataResult<>(customerMapper.getDto(customer), Messages.DELETED);
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
        log.info(Messages.UPDATED + " " + customer);
        return new SuccessDataResult<>(customerMapper.getDto(customer), Messages.UPDATED);
    }

    @Override
    public DataResult<List<CustomerDto>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        log.info(Messages.LISTED);
        return new SuccessDataResult<>(customers.stream().map(customerMapper::getDto).collect(Collectors.toList()), Messages.LISTED);
    }

    @Override
    public DataResult<CustomerDto> getByCustomerForIdentityNumber(String identityNumber) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(CustomerNotFoundException::new);
        log.info(Messages.CUSTOMER_FOR_IDENTITY_NUMBER + " " + customer);
        return new SuccessDataResult<>(customerMapper.getDto(customer), Messages.CUSTOMER_FOR_IDENTITY_NUMBER);
    }
}
