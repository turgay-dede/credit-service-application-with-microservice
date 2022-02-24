package com.turgaydede.entities.dtos;

import com.turgaydede.entities.CreditConsent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditDto {
    private String id;
    private String identityNumber;
    private CreditConsent creditConsent;
    private int creditLimit;
}
