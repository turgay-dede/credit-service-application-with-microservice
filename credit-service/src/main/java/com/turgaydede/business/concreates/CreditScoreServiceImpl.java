package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.constants.Messages;
import com.turgaydede.entities.CreditScore;
import com.turgaydede.repositories.CreditScoreRepository;
import com.turgaydede.util.result.DataResult;
import com.turgaydede.util.result.SuccessDataResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Log4j2
public class CreditScoreServiceImpl implements CreditScoreService {
    private final CreditScoreRepository creditScoreRepository;
    private final Random random;

    public CreditScoreServiceImpl(CreditScoreRepository creditScoreRepository, Random random) {
        this.creditScoreRepository = creditScoreRepository;
        this.random = random;
    }

    @Override
    public DataResult<CreditScore> setCreditScore(String identityNumber) {
        int score = random.nextInt(1500);
        CreditScore creditScore = CreditScore.builder()
                .creditScore(score)
                .identityNumber(identityNumber)
                .build();
        log.info(Messages.CREDIT_SCORE_ASSIGNED + " " + creditScore);
        return new SuccessDataResult<>(creditScoreRepository.save(creditScore));
    }
}
