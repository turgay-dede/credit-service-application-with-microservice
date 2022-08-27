package com.turgaydede.entity.dto;

import com.turgaydede.entity.CreditConsent;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CreditDto {
    private String id;
    @NotEmpty(message = "Kimlik numarası boş geçilemez")
    private String identityNumber;

    private CreditConsent creditConsent;

    private int creditLimit;
}
