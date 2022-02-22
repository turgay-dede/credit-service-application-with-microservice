package com.turgaydede.business.abstracts;

import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditListDto;
import com.turgaydede.entities.dtos.CreditResponseDto;
import com.turgaydede.entities.dtos.CustomerDto;

import java.util.List;

public interface CreditService {
    CreditResponseDto creditApplication(CustomerDto customerDto);
    CreditDto delete(String identityNumber);
    CreditDto update(CreditDto creditDto);
    List<CreditListDto> getAll();
    Credit getCreditByIdentityNumber(String identityNumber);
}
