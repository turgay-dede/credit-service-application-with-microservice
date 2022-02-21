package com.turgaydede.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document
public class Credit {
    @Id
    private String id;
    private String customerIdentityNumber;
    @Transient
    private CreditConsent creditConsent;
    private int creditLimit;

    public static Credit rejectAccount() {
        return new CreditBuilder().creditConsent(CreditConsent.REJECT).build();
    }

    public static Credit silverAccount(Customer customer) {
        return new CreditBuilder()
                .customerIdentityNumber(customer.getIdentityNumber())
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(10000)
                .build();
    }

    public static Credit goldAccount(Customer customer) {
        return new CreditBuilder()
                .customerIdentityNumber(customer.getIdentityNumber())
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(20000)
                .build();
    }

    public static Credit platinumAccount(Customer customer, int creditLimitMultiplier) {
        return new CreditBuilder()
                .customerIdentityNumber(customer.getIdentityNumber())
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(customer.getMonthlyIncome() * creditLimitMultiplier)
                .build();
    }

}
