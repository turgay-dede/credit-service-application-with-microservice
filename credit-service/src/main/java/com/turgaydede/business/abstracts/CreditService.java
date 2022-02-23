package com.turgaydede.business.abstracts;

import com.turgaydede.dtos.CustomerDto;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditResponseDto;

import java.util.List;

public interface CreditService {
    CreditResponseDto creditApplication(CustomerDto customerDto);
    CreditDto delete(String identityNumber);
    CreditDto update(CreditDto creditDto);
    List<CreditDto> getAll();
    Credit getCreditByIdentityNumber(String identityNumber);
}
