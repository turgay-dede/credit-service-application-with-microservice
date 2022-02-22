package com.turgaydede.repositories;

import com.turgaydede.entities.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CreditRepository extends MongoRepository<Credit,String> {
    Optional<Credit> findCreditByIdentityNumber(String identityNumber);
}
