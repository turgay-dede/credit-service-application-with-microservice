package com.turgaydede.util.converter;

import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditResponseDto;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditResponseDtoConverter {
    public CreditResponseDto convert(Credit credit){
        return CreditResponseDto.builder()
                .creditConsent(credit.getCreditConsent())
                .creditLimit(credit.getCreditLimit())
                .build();
    }
}
