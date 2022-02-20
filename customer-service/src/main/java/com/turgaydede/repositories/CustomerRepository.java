package com.turgaydede.repositories;

import com.turgaydede.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByIdentityNumber(String identityNumber);
}
