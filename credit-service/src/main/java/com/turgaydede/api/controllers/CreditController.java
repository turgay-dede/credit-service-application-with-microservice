package com.turgaydede.api.controllers;

import com.turgaydede.business.abstracts.CreditService;
import com.turgaydede.entity.dto.CreditDto;
import com.turgaydede.entity.dto.CreditResponseDto;
import com.turgaydede.entity.dto.CustomerDto;
import com.turgaydede.util.result.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public ResponseEntity<DataResult<CreditResponseDto>> creditApplication(@Valid @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(creditService.creditApplication(customerDto));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public  ResponseEntity<DataResult<CreditDto>> update(@Valid @RequestBody CreditDto creditDto){
        return ResponseEntity.ok(creditService.update(creditDto));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public  ResponseEntity<DataResult<CreditDto>> delete(@RequestParam String identityNumber){
        return ResponseEntity.ok(creditService.delete(identityNumber));
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public  ResponseEntity<DataResult<List<CreditDto>>> getAll(){
        return ResponseEntity.ok(creditService.getAll());
    }

    @GetMapping("/get/credit/identity-number")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public  ResponseEntity<DataResult<CreditDto>> getCreditByIdentityNumber(@RequestParam String identityNumber){
        return ResponseEntity.ok(creditService.getCreditByIdentityNumber(identityNumber));
    }

}
