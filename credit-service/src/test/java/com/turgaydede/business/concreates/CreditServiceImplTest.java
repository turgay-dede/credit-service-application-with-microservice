package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.CreditConsent;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.feign.customer.CustomerFeignClient;
import com.turgaydede.repositories.CreditRepository;
import com.turgaydede.util.converter.CreditDtoConverter;
import com.turgaydede.util.converter.CreditResponseDtoConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {
    @Mock private  CreditRepository creditRepository;
    @Mock private  CreditScoreService creditScoreService;
    @Mock private  CreditResponseDtoConverter creditResponseDtoConverter;
    @Mock private  CreditDtoConverter creditDtoConverter;
    @Mock private  CustomerFeignClient feignClient;

    @InjectMocks
    private CreditServiceImpl creditServiceImpl;

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

}