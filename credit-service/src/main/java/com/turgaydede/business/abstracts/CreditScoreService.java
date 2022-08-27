package com.turgaydede.business.abstracts;

import com.turgaydede.entity.CreditScore;
import com.turgaydede.util.result.DataResult;

public interface CreditScoreService {
    DataResult<CreditScore> setCreditScore(String identityNumber);
}
