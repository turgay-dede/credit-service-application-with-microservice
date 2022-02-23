package com.turgaydede.api.controllers;

import com.turgaydede.business.abstracts.CreditService;
import com.turgaydede.dtos.CustomerDto;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditListDto;
import com.turgaydede.entities.dtos.CreditResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/credits")
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/credit-application")
    public CreditResponseDto creditApplication(@RequestBody CustomerDto customerDto){
        return creditService.creditApplication(customerDto);
    }

    @PutMapping("/update")
    public CreditDto update(@RequestBody CreditDto creditDto){
        return creditService.update(creditDto);
    }

    @DeleteMapping("/delete")
    public CreditDto delete(@RequestParam String identityNumber){
        return creditService.delete(identityNumber);
    }

    @GetMapping("/getall")
    public List<CreditListDto> getAll(){
        return creditService.getAll();
    }

    @GetMapping("/get-credit/identity-number")
    public Credit getCreditByIdentityNumber(@RequestParam String identityNumber){
        return creditService.getCreditByIdentityNumber(identityNumber);
    }

}
