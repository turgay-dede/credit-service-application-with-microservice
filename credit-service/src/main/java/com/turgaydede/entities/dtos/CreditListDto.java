package com.turgaydede.entities.dtos;

import lombok.Data;

@Data
public class CreditListDto {
    private String id;
    private String identityNumber;
    private int creditLimit;
}
