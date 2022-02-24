package com.turgaydede.entities;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Ad Soyad boş geçilemez")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Kimlik numarası boş geçilemez")
    @Column(name = "identity_number")
    private String identityNumber;

    @NotEmpty(message = "Aylık gelir boş geçilemez")
    @Positive(message = "Aylık gelir negatif değer olamaz")
    @Column(name = "monthly_income")
    private int monthlyIncome;

    @NotEmpty(message = "Telefon numarası boş geçilemez")
    @Column(name = "phone_number")
    private String phoneNumber;
}
