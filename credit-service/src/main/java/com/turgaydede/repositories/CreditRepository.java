package com.turgaydede.repositories;

import com.turgaydede.entities.Credit;
import com.turgaydede.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CreditRepository extends MongoRepository<Credit,String> {
}
