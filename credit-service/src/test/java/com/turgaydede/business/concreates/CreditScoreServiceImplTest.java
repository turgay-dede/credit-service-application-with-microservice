package com.turgaydede.business.concreates;

import com.turgaydede.entities.CreditScore;
import com.turgaydede.repositories.CreditScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreditScoreServiceImplTest {

    private  CreditScoreRepository creditScoreRepository;
    private Random random;
    private CreditScoreServiceImpl creditScoreServiceImpl;

    @BeforeEach
    void setUp() {
        creditScoreRepository = Mockito.mock(CreditScoreRepository.class);
        random = Mockito.mock(Random.class);
        creditScoreServiceImpl = new CreditScoreServiceImpl(creditScoreRepository,random);
    }

    @Test
    void setCreditScore() {
        int score = 600;
        String identityNumber = "20000000000";
        CreditScore creditScore = CreditScore.builder()
                .creditScore(score)
                .identityNumber(identityNumber)
                .build();

        when(random.nextInt(1500)).thenReturn(score);
        when(creditScoreRepository.save(creditScore)).thenReturn(creditScore);

        CreditScore result = creditScoreServiceImpl.setCreditScore(identityNumber).getData();

        Assertions.assertEquals(creditScore,result);
        verify(creditScoreRepository).save(creditScore);
        verify(random).nextInt(1500);
    }
}