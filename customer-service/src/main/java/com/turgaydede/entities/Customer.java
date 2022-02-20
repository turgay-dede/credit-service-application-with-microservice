package com.turgaydede.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "monthly_income")
    private int monthlyIncome;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "credit_score")
    private int creditScore;
}
