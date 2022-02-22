package com.turgaydede.repositories;

import com.turgaydede.entities.CreditScore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditScoreRepository extends MongoRepository<CreditScore,String> {
}
