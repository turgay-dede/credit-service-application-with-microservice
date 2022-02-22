package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.entities.CreditScore;
import com.turgaydede.repositories.CreditScoreRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CreditScoreServiceImpl implements CreditScoreService {
    private final CreditScoreRepository creditScoreRepository;
    private final Random random;

    public CreditScoreServiceImpl(CreditScoreRepository creditScoreRepository,Random random) {
        this.creditScoreRepository = creditScoreRepository;
        this.random = random;
    }

    @Override
    public CreditScore setCreditScore(String identityNumber) {
        int score = random.nextInt(1500);
        CreditScore creditScore = CreditScore.builder()
                .creditScore(score)
                .identityNumber(identityNumber)
                .build();
        return creditScoreRepository.save(creditScore);
    }
}
