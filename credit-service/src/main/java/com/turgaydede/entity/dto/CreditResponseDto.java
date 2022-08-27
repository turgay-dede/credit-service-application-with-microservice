package com.turgaydede.entity.dto;

import com.turgaydede.entity.CreditConsent;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CreditResponseDto {
    private CreditConsent creditConsent;
    private int creditLimit;
}
