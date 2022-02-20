package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CustomerService;
import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.entities.dtos.CustomerUpdateRequest;
import com.turgaydede.exceptions.CustomerNotFoundException;
import com.turgaydede.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto add(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto delete(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto update(CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = modelMapper.map(customerUpdateRequest, Customer.class);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getByCustomerForIdentityNumber(String identityNumber) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(CustomerNotFoundException::new);
        return modelMapper.map(customer, CustomerDto.class);
    }
}
