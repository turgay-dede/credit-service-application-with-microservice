package com.turgaydede.util.converter;

import com.turgaydede.entity.Credit;
import com.turgaydede.entity.dto.CreditResponseDto;
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
