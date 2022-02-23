package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.business.abstracts.CreditService;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.CreditConsent;
import com.turgaydede.entities.CreditScore;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditListDto;
import com.turgaydede.entities.dtos.CreditResponseDto;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.exceptions.CreditNotFoundException;
import com.turgaydede.feign.customer.CustomerFeignClient;
import com.turgaydede.repositories.CreditRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CreditScoreService creditScoreService;
    private final ModelMapper modelMapper;
    private final CustomerFeignClient feignClient;


    public CreditServiceImpl(CreditRepository creditRepository, ModelMapper modelMapper, @Lazy CreditScoreService creditScoreService, CustomerFeignClient feignClient) {
        this.creditRepository = creditRepository;
        this.modelMapper = modelMapper;
        this.creditScoreService = creditScoreService;
        this.feignClient = feignClient;
    }

    @Override
    public CreditResponseDto creditApplication(CustomerDto customerDto) {
        CreditScore creditScore = creditScoreService.setCreditScore(customerDto.getIdentityNumber());

        Credit credit = createAccountForCreditScoreAndMonthlyIncome(creditScore, customerDto);
        if (isConfirmCreditApplication(credit)) {
            creditRepository.save(credit);
            feignClient.add(customerDto);
        }
        return modelMapper.map(credit, CreditResponseDto.class);
    }


    @Override
    public CreditDto delete(String identityNumber) {
        Credit credit = creditRepository.findCreditByIdentityNumber(identityNumber).orElseThrow(CreditNotFoundException::new);
        creditRepository.delete(credit);
        return modelMapper.map(credit, CreditDto.class);

    }

    @Override
    public CreditDto update(CreditDto creditDto) {
        Credit credit = modelMapper.map(creditDto, Credit.class);
        creditRepository.save(credit);
        return modelMapper.map(credit, CreditDto.class);
    }

    @Override
    public List<CreditListDto> getAll() {
        List<Credit> list = creditRepository.findAll();
        return list.stream().map(credit -> modelMapper.map(credit, CreditListDto.class)).collect(Collectors.toList());
    }

    @Override
    public Credit getCreditByIdentityNumber(String identityNumber) {
        return creditRepository.findCreditByIdentityNumber(identityNumber).orElseThrow(CreditNotFoundException::new);
    }

    private Credit createAccountForCreditScoreAndMonthlyIncome(CreditScore creditScore, CustomerDto customerDto) {
        final int CREDIT_LIMIT_MULTIPLIER = 4;
        return isCreditScoreBetween500_1000AndMonthlyIncomeLessThan5000(creditScore, customerDto) ? Credit.silverAccount(customerDto) :
                isCreditScoreBetween500_1000AndMonthlyIncomeGreaterThanEqual5000(creditScore, customerDto) ? Credit.goldAccount(customerDto) :
                        isCreditScoreGreaterThanEqual1000(creditScore) ? Credit.platinumAccount(customerDto, CREDIT_LIMIT_MULTIPLIER) : Credit.rejectAccount();

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
