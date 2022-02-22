package com.turgaydede.entities.dtos;

import com.turgaydede.entities.CreditConsent;
import com.turgaydede.entities.Customer;
import lombok.Data;

@Data
public class CreditDto {
    private String id;
    private String identityNumber;
    private CreditConsent creditConsent;
    private int creditLimit;
    private int creditScore;
}
