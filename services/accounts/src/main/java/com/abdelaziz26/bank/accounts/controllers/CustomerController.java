package com.abdelaziz26.bank.accounts.controllers;

import com.abdelaziz26.bank.accounts.dto.CustomerDetailsDto;
import com.abdelaziz26.bank.accounts.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam String mobile)
    {
        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobile);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
