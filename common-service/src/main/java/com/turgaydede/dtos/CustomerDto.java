package com.turgaydede.dtos;

public class CustomerDto {
    private int id;
    private String fullName;
    private String identityNumber;
    private int monthlyIncome;
    private String phoneNumber;

    public CustomerDto() {
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
}
