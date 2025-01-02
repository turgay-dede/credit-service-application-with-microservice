package com.turgaydede.entity;

import com.turgaydede.entity.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "credits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identityNumber;

    @Enumerated(EnumType.STRING)
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
