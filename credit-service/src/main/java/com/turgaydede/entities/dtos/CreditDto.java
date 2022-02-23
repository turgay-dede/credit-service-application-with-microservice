package com.turgaydede.entities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.turgaydede.entities.CreditConsent;
import lombok.Data;

@Data
public class CreditDto {
    private String id;
    private String identityNumber;
    private CreditConsent creditConsent;
    private int creditLimit;
}
