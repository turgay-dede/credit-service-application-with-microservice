package com.turgaydede.entity;

import com.turgaydede.entity.dto.CustomerDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Builder
@ToString
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Credit implements Serializable {
    @Id
    private String id;
    private String identityNumber;
    private CreditConsent creditConsent;
    private int creditLimit;

    public static Credit rejectAccount(CustomerDto customerDto) {
        return new CreditBuilder()
                .identityNumber(customerDto.getIdentityNumber())
                .creditConsent(CreditConsent.REJECT)
                .build();
    }

    public static Credit silverAccount(CustomerDto customerDto) {
        return new CreditBuilder()
                .identityNumber(customerDto.getIdentityNumber())
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(10000)
                .build();
    }

    public static Credit goldAccount(CustomerDto customerDto) {
        return new CreditBuilder()
                .identityNumber(customerDto.getIdentityNumber())
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(20000)
                .build();
    }

    public static Credit platinumAccount(CustomerDto customerDto, int creditLimitMultiplier) {
        return new CreditBuilder()
                .identityNumber(customerDto.getIdentityNumber())
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(customerDto.getMonthlyIncome() * creditLimitMultiplier)
                .build();
    }

}
