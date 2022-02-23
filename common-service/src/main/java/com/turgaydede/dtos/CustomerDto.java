package com.turgaydede.dtos;

import java.util.Objects;

public class CustomerDto {
    private int id;
    private String fullName;
    private String identityNumber;
    private int monthlyIncome;
    private String phoneNumber;

    public CustomerDto(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.identityNumber = builder.identityNumber;
        this.monthlyIncome = builder.monthlyIncome;
        this.phoneNumber = builder.phoneNumber;
    }

    public CustomerDto(int id, String fullName, String identityNumber, int monthlyIncome, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.identityNumber = identityNumber;
        this.monthlyIncome = monthlyIncome;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id == that.id && monthlyIncome == that.monthlyIncome && Objects.equals(fullName, that.fullName) && Objects.equals(identityNumber, that.identityNumber) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, identityNumber, monthlyIncome, phoneNumber);
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
