package com.turgaydede.entities.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private int id;

    @NotEmpty(message = "Ad Soyad boş geçilemez")
    private String fullName;

    @NotEmpty(message = "Kimlik numarası boş geçilemez")
    private String identityNumber;

    @Positive(message = "Aylık gelir negatif değer olamaz")
    private int monthlyIncome;

    @NotEmpty(message = "Telefon numarası boş geçilemez")
    private String phoneNumber;

    public CustomerDto(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.identityNumber = builder.identityNumber;
        this.monthlyIncome = builder.monthlyIncome;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class Builder {
        private int id;
        private String fullName;
        private String identityNumber;
        private int monthlyIncome;
        private String phoneNumber;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder identityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
            return this;
        }

        public Builder monthlyIncome(int monthlyIncome) {
            this.monthlyIncome = monthlyIncome;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(this);
        }

    }
}
