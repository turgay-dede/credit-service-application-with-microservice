package com.turgaydede.util.converter;

import com.turgaydede.entity.Credit;
import com.turgaydede.entity.dto.CreditDto;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditDtoConverter {
    public CreditDto convert(Credit credit){
        return CreditDto.builder()
                .id(credit.getId())
                .identityNumber(credit.getIdentityNumber())
                .creditConsent(credit.getCreditConsent())
                .creditLimit(credit.getCreditLimit())
                .build();
    }
}
