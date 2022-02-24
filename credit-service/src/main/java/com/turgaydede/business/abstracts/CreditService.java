package com.turgaydede.business.abstracts;

import com.turgaydede.dtos.CustomerDto;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditResponseDto;
import com.turgaydede.util.result.DataResult;

import java.util.List;

public interface CreditService {
    DataResult<CreditResponseDto> creditApplication(CustomerDto customerDto);
    DataResult<CreditDto> delete(String identityNumber);
    DataResult<CreditDto> update(CreditDto creditDto);
    DataResult<List<CreditDto>> getAll();
    DataResult<Credit> getCreditByIdentityNumber(String identityNumber);
}
