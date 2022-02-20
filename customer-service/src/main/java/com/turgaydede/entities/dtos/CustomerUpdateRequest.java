package com.turgaydede.entities.dtos;
import lombok.Data;

@Data
public class CustomerUpdateRequest {
    private int id;
    private String fullName;
    private String identityNumber;
    private int monthlyIncome;
    private int creditScore;
    private String phoneNumber;
}
