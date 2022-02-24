package com.turgaydede.util.converter;

import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditDto;
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
