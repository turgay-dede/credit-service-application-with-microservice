package com.turgaydede.api.controllers;

import com.turgaydede.business.abstracts.CreditService;
import com.turgaydede.entities.Credit;
import com.turgaydede.entities.dtos.CreditDto;
import com.turgaydede.entities.dtos.CreditResponseDto;
import com.turgaydede.entities.dtos.CustomerDto;
import com.turgaydede.util.result.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/rest/credits")
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/credit-application")
    public ResponseEntity<DataResult<CreditResponseDto>> creditApplication(@Valid @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(creditService.creditApplication(customerDto));
    }

    @PutMapping("/update")
    public  ResponseEntity<DataResult<CreditDto>> update(@Valid @RequestBody CreditDto creditDto){
        return ResponseEntity.ok(creditService.update(creditDto));
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<DataResult<CreditDto>> delete(@RequestParam String identityNumber){
        return ResponseEntity.ok(creditService.delete(identityNumber));
    }

    @GetMapping("/getall")
    public  ResponseEntity<DataResult<List<CreditDto>>> getAll(){
        return ResponseEntity.ok(creditService.getAll());
    }

    @GetMapping("/get/credit/identity-number")
    public  ResponseEntity<DataResult<CreditDto>> getCreditByIdentityNumber(@RequestParam String identityNumber){
        return ResponseEntity.ok(creditService.getCreditByIdentityNumber(identityNumber));
    }

}
