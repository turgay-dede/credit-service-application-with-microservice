package com.turgaydede.business.abstracts;
import com.turgaydede.entity.dto.CreditDto;
import com.turgaydede.entity.dto.CreditResponseDto;
import com.turgaydede.entity.dto.CustomerDto;
import com.turgaydede.util.result.DataResult;

import java.util.List;

public interface CreditService {
    DataResult<CreditResponseDto> creditApplication(CustomerDto customerDto);
    DataResult<CreditDto> delete(String identityNumber);
    DataResult<CreditDto> update(CreditDto creditDto);
    DataResult<List<CreditDto>> getAll();
    DataResult<CreditDto> getCreditByIdentityNumber(String identityNumber);
}
