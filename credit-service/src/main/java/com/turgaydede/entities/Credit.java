package com.turgaydede.entities;

import com.turgaydede.entities.dtos.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    @Id
    private String id;
    private String identityNumber;
    @Transient
    private CreditConsent creditConsent;
    private int creditLimit;

    public static Credit rejectAccount() {
        return new CreditBuilder().creditConsent(CreditConsent.REJECT).build();
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
