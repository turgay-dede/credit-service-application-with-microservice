package com.turgaydede.business.abstracts;

import com.turgaydede.entities.dtos.CreditResponseDto;
import com.turgaydede.entities.dtos.CustomerDto;

public interface CreditService {
    CreditResponseDto creditApplication(CustomerDto customerDto);
}
