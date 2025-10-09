package com.abdelaziz26.bank.accounts.services.clients;

import com.abdelaziz26.bank.accounts.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansClientFallBack implements LoansClient{
    @Override
    public ResponseEntity<LoanDto> fetchLoan(String mobileNumber) {
        return null;
    }
}
