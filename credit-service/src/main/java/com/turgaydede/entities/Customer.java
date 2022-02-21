package com.turgaydede.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String fullName;
    private String identityNumber;
    private int monthlyIncome;
    private String phoneNumber;
    private int creditScore;
}
