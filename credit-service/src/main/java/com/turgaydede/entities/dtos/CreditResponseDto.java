package com.turgaydede.entities.dtos;

import com.turgaydede.entities.CreditConsent;
import lombok.Data;
@Data
public class CreditResponseDto {
    private CreditConsent creditConsent;
    private int creditLimit;
}
