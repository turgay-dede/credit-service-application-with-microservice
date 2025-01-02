package com.turgaydede.entity.dto;

import com.turgaydede.entity.CreditConsent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private Long id;
    private String identityNumber;
    private CreditConsent creditConsent;
    private int creditLimit;
}
