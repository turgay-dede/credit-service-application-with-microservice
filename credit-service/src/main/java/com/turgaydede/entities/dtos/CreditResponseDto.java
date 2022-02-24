package com.turgaydede.entities.dtos;

import com.turgaydede.entities.CreditConsent;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CreditResponseDto {
    private CreditConsent creditConsent;
    private int creditLimit;
}
