package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.business.abstracts.CreditService;
import com.turgaydede.entity.Credit;
import com.turgaydede.entity.CreditConsent;
import com.turgaydede.entity.CreditScore;
import com.turgaydede.entity.dto.CreditDto;
import com.turgaydede.entity.dto.CreditResponseDto;
import com.turgaydede.entity.dto.CustomerDto;
import com.turgaydede.exceptions.CreditNotFoundException;
import com.turgaydede.feign.customer.CustomerFeignClient;
import com.turgaydede.mapper.CreditMapper;
import com.turgaydede.mapper.CreditResponseMapper;
import com.turgaydede.repositories.CreditRepository;
import com.turgaydede.util.constant.Messages;
import com.turgaydede.util.result.DataResult;
import com.turgaydede.util.result.SuccessDataResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CreditScoreService creditScoreService;
    private final CreditResponseMapper creditResponseMapper;
    private final CreditMapper creditMapper;
    private final CustomerFeignClient feignClient;


    public CreditServiceImpl(CreditRepository creditRepository, @Lazy CreditScoreService creditScoreService, CreditResponseMapper creditResponseMapper, CreditMapper creditMapper, CustomerFeignClient feignClient) {
        this.creditRepository = creditRepository;
        this.creditScoreService = creditScoreService;
        this.creditResponseMapper = creditResponseMapper;
        this.creditMapper = creditMapper;
        this.feignClient = feignClient;
    }

    @Override
    public DataResult<CreditResponseDto> creditApplication(CustomerDto customerDto) {
        CreditScore creditScore = creditScoreService.setCreditScore(customerDto.getIdentityNumber()).getData();
        Credit credit = createAccountForCreditScoreAndMonthlyIncome(creditScore, customerDto);

        if (isConfirmCreditApplication(credit))
            feignClient.add(customerDto);

        creditRepository.save(credit);
        log.info(Messages.CREDIT_APPLICATION);
        return new SuccessDataResult<>(creditResponseMapper.getDto(credit), Messages.CREDIT_APPLICATION);
    }


    @Override
    public DataResult<CreditDto> delete(String identityNumber) {
        Credit credit = creditRepository.findCreditByIdentityNumber(identityNumber).orElseThrow(CreditNotFoundException::new);
        creditRepository.delete(credit);
        log.info(Messages.DELETED+" "+ credit);
        return new SuccessDataResult<>(creditMapper.getDto(credit), Messages.DELETED);

    }

    @Override
    public DataResult<CreditDto> update(CreditDto creditDto) {
        Credit credit = Credit.builder()
                .id(creditDto.getId())
                .identityNumber(creditDto.getIdentityNumber())
                .creditConsent(creditDto.getCreditConsent())
                .creditLimit(creditDto.getCreditLimit())
                .build();
        creditRepository.save(credit);
        log.info(Messages.UPDATED+" "+ credit);
        return new SuccessDataResult<>(creditMapper.getDto(credit), Messages.UPDATED);
    }

    @Override
    public DataResult<List<CreditDto>> getAll() {
        List<Credit> list = creditRepository.findAll();
        log.info(Messages.LISTED);
        List<CreditDto> data = creditMapper.getDtoList(list);
        return new SuccessDataResult<>(data, Messages.LISTED);
    }

    @Override
    public DataResult<CreditDto> getCreditByIdentityNumber(String identityNumber) {
        Credit credit = creditRepository.findCreditByIdentityNumber(identityNumber).orElseThrow(CreditNotFoundException::new);
        log.info(Messages.CREDIT_FOR_IDENTITY_NUMBER+" "+ credit);
        return new SuccessDataResult<>(creditMapper.getDto(credit), Messages.CREDIT_FOR_IDENTITY_NUMBER);
    }

    private Credit createAccountForCreditScoreAndMonthlyIncome(CreditScore creditScore, CustomerDto customerDto) {
        final int CREDIT_LIMIT_MULTIPLIER = 4;
        return isCreditScoreBetween500_1000AndMonthlyIncomeLessThan5000(creditScore, customerDto) ? Credit.silverAccount(customerDto) :
                isCreditScoreBetween500_1000AndMonthlyIncomeGreaterThanEqual5000(creditScore, customerDto) ? Credit.goldAccount(customerDto) :
                        isCreditScoreGreaterThanEqual1000(creditScore) ? Credit.platinumAccount(customerDto, CREDIT_LIMIT_MULTIPLIER) : Credit.rejectAccount(customerDto);

    }

    private boolean isCreditScoreBetween500_1000AndMonthlyIncomeLessThan5000(CreditScore creditScore, CustomerDto customerDto) {
        return creditScore.getCreditScore() > 500 && creditScore.getCreditScore() < 1000 && customerDto.getMonthlyIncome() < 5000;
    }

    // Equality has been added to prevent bugs when Monthly Income is equal to 5000.
    private boolean isCreditScoreBetween500_1000AndMonthlyIncomeGreaterThanEqual5000(CreditScore creditScore, CustomerDto customerDto) {
        return creditScore.getCreditScore() > 500 && creditScore.getCreditScore() < 1000 && customerDto.getMonthlyIncome() >= 5000;
    }

    private boolean isCreditScoreGreaterThanEqual1000(CreditScore creditScore) {
        return creditScore.getCreditScore() >= 1000;
    }

    private boolean isConfirmCreditApplication(Credit credit) {
        return CreditConsent.CONFIRM.equals(credit.getCreditConsent());
    }
}
