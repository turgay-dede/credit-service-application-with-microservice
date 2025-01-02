package com.turgaydede.repositories;

import com.turgaydede.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CreditRepository extends JpaRepository<Credit,Long> {
    Optional<Credit> findCreditByIdentityNumber(String identityNumber);
}
