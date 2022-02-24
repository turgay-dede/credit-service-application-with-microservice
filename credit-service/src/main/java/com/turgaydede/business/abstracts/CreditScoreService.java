package com.turgaydede.business.abstracts;

import com.turgaydede.entities.CreditScore;
import com.turgaydede.util.result.DataResult;

public interface CreditScoreService {
    DataResult<CreditScore> setCreditScore(String identityNumber);
}
