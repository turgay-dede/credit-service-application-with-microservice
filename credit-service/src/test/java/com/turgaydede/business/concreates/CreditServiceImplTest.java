package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.entity.Credit;
import com.turgaydede.entity.CreditConsent;
import com.turgaydede.entity.CreditScore;
import com.turgaydede.entity.dto.CreditDto;
import com.turgaydede.entity.dto.CreditResponseDto;
import com.turgaydede.entity.dto.CustomerDto;
import com.turgaydede.feign.customer.CustomerFeignClient;
import com.turgaydede.repositories.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {
    @Mock private  CreditRepository creditRepository;
    @Mock private  CreditScoreService creditScoreService;
    @Mock private  CustomerFeignClient feignClient;

    @InjectMocks
    private CreditServiceImpl creditServiceImpl;

    @Test
    void rejectAccount() {
        CustomerDto customerDto = generateCustomerDto();
        CreditScore creditScore = generateCreditScore();
        creditScore.setCreditScore(400);
        Credit credit = null;

        if(creditScore.getCreditScore() < 500){
            credit = Credit.rejectAccount(customerDto);
        }

        assertEquals(credit,Credit.rejectAccount(customerDto));
    }

    @Test
    void silverAccount(){
        CustomerDto customerDto = generateCustomerDto();
        customerDto.setMonthlyIncome(4000);
        CreditScore creditScore = generateCreditScore();
        creditScore.setCreditScore(600);
        Credit credit = null;

        if(creditScore.getCreditScore() > 500 && creditScore.getCreditScore() < 1000 && customerDto.getMonthlyIncome() < 5000){
            credit = Credit.silverAccount(customerDto);
        }

        assertEquals(credit,Credit.silverAccount(customerDto));
    }

    @Test
    void goldAccount(){
        CustomerDto customerDto = generateCustomerDto();
        customerDto.setMonthlyIncome(6000);
        CreditScore creditScore = generateCreditScore();
        creditScore.setCreditScore(600);
        Credit credit = null;

        if(creditScore.getCreditScore() > 500 && creditScore.getCreditScore() < 1000 && customerDto.getMonthlyIncome() >= 5000){
            credit = Credit.goldAccount(customerDto);
        }

        assertEquals(credit,Credit.goldAccount(customerDto));
    }

    @Test
    void platinumAccount(){
        final int CREDIT_LIMIT_MULTIPLIER = 4;
        CustomerDto customerDto = generateCustomerDto();
        CreditScore creditScore = generateCreditScore();
        creditScore.setCreditScore(1100);
        Credit credit = null;

        if(creditScore.getCreditScore() >= 1000){
            credit = Credit.platinumAccount(customerDto,CREDIT_LIMIT_MULTIPLIER);
        }

        assertEquals(credit,Credit.platinumAccount(customerDto,CREDIT_LIMIT_MULTIPLIER));
    }

    @Test
    void delete() {
        Credit credit = generateConfirmCredit();

        creditRepository.delete(credit);

        verify(creditRepository, Mockito.times(1)).delete(credit);
    }

    @Test
    void update() {
        CreditDto creditDto = generateConfirmCreditDto();
        Credit credit = Credit.builder()
                .id(creditDto.getId())
                .identityNumber(creditDto.getIdentityNumber())
                .creditConsent(creditDto.getCreditConsent())
                .creditLimit(creditDto.getCreditLimit())
                .build();

        when(creditRepository.save(credit)).thenReturn(credit);

        CreditDto result = creditServiceImpl.update(creditDto).getData();
        assertNotEquals(result, creditDto);
        verify(creditRepository).save(Mockito.any(Credit.class));
    }

    @Test
    void getAll() {
        List<Credit> creditList = new ArrayList<>();
        Credit credit1 = generateConfirmCredit();
        Credit credit2 = generateConfirmCredit();
        creditList.add(credit1);
        creditList.add(credit2);

        when(creditRepository.findAll()).thenReturn(creditList);

        List<CreditDto> result = creditServiceImpl.getAll().getData();
        assertEquals(creditList.size(), result.size());
    }

    @Test
    void getCreditByIdentityNumber() {
        String identityNumber = "20000000000";
        CreditDto creditDto = generateConfirmCreditDto();
        Credit credit = Credit.builder()
                .id("1")
                .identityNumber("20000000000")
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(10000)
                .build();

        when(creditRepository.findCreditByIdentityNumber(identityNumber)).thenReturn(Optional.of(credit));

        CreditDto result = creditServiceImpl.getCreditByIdentityNumber(credit.getIdentityNumber()).getData();

        assertEquals(result, creditDto);
        verify(creditRepository).findCreditByIdentityNumber(identityNumber);
    }


    private Credit generateConfirmCredit(){
        return Credit.builder()
                .id("1")
                .identityNumber("20000000000")
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(10000)
                .build();
    }

    private CreditDto generateConfirmCreditDto(){
        return CreditDto.builder()
                .id("1")
                .identityNumber("20000000000")
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(10000)
                .build();
    }

    private CreditScore generateCreditScore(){
        return CreditScore.builder()
                .id("1")
                .identityNumber("20000000000")
                .creditScore(100)
                .build();
    }

    private CustomerDto generateCustomerDto() {
        return new CustomerDto.Builder()
                .id(1)
                .fullName("Turgay Dede")
                .identityNumber("20000000000")
                .monthlyIncome(8000)
                .phoneNumber("5400000000")
                .build();
    }

    private CreditResponseDto generateRejectCreditResponseDto() {
        return CreditResponseDto.builder()
                .creditConsent(CreditConsent.REJECT)
                .creditLimit(0)
                .build();
    }

    private CreditResponseDto generateConfirmCreditResponseDto() {
        return CreditResponseDto.builder()
                .creditConsent(CreditConsent.CONFIRM)
                .creditLimit(10000)
                .build();
    }

}