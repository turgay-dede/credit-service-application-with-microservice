package com.turgaydede.business.concreates;

import com.turgaydede.business.abstracts.CreditScoreService;
import com.turgaydede.business.abstracts.CreditService;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.CreditConsent;
import com.turgaydede.entities.CreditScore;
import com.turgaydede.entities.Customer;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditListDto;
import com.turgaydede.entities.dtos.CreditResponseDto;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.exceptions.CreditNotFoundException;
import com.turgaydede.repositories.CreditRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CreditScoreService creditScoreService;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;


    public CreditServiceImpl(CreditRepository creditRepository, ModelMapper modelMapper, @Lazy CreditScoreService creditScoreService, RestTemplate restTemplate) {
        this.creditRepository = creditRepository;
        this.modelMapper = modelMapper;
        this.creditScoreService = creditScoreService;
        this.restTemplate = restTemplate;
    }

    @Override
    public CreditResponseDto creditApplication(CustomerDto customerDto) {
        CreditScore creditScore = creditScoreService.setCreditScore(customerDto.getIdentityNumber());
        Customer customer = modelMapper.map(customerDto, Customer.class);

        Credit credit = createAccountForCreditScoreAndMonthlyIncome(creditScore, customer);
        if (isConfirmCreditApplication(credit)) {
            creditRepository.save(credit);
            customerSave(customerDto);
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

    private Credit createAccountForCreditScoreAndMonthlyIncome(CreditScore creditScore, Customer customer) {
        final int CREDIT_LIMIT_MULTIPLIER = 4;
        return isCreditScoreBetween500_1000AndMonthlyIncomeLessThan5000(creditScore, customer) ? Credit.silverAccount(customer) :
                isCreditScoreBetween500_1000AndMonthlyIncomeGreaterThanEqual5000(creditScore, customer) ? Credit.goldAccount(customer) :
                        isCreditScoreGreaterThanEqual1000(creditScore) ? Credit.platinumAccount(customer, CREDIT_LIMIT_MULTIPLIER) : Credit.rejectAccount();

    }

    private boolean isCreditScoreBetween500_1000AndMonthlyIncomeLessThan5000(CreditScore creditScore, Customer customer) {
        return creditScore.getCreditScore() > 500 && creditScore.getCreditScore() < 1000 && customer.getMonthlyIncome() < 5000;
    }

    // Equality has been added to prevent bugs when Monthly Income is equal to 5000.
    private boolean isCreditScoreBetween500_1000AndMonthlyIncomeGreaterThanEqual5000(CreditScore creditScore, Customer customer) {
        return creditScore.getCreditScore() > 500 && creditScore.getCreditScore() < 1000 && customer.getMonthlyIncome() >= 5000;
    }

    private boolean isCreditScoreGreaterThanEqual1000(CreditScore creditScore) {
        return creditScore.getCreditScore() >= 1000;
    }

    private boolean isConfirmCreditApplication(Credit credit) {
        return CreditConsent.CONFIRM.equals(credit.getCreditConsent());
    }

    private void customerSave(CustomerDto theCustomerDto) {
        final String URL = "http://localhost:8081/rest/customers/add";
        HttpEntity<CustomerDto> customerDtoHttpEntity = new HttpEntity<>(theCustomerDto);
        restTemplate.exchange(URL, HttpMethod.POST, customerDtoHttpEntity, CustomerDto.class);
    }
}
