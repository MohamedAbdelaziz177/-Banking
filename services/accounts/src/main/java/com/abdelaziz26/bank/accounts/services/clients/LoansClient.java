package com.abdelaziz26.bank.accounts.services.clients;

import com.abdelaziz26.bank.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "loans",
        url = "${microservices.loans.url}",
        fallback = LoansClientFallBack.class
)
public interface LoansClient {

    @GetMapping("/api/loans/{mobileNumber}")
    ResponseEntity<LoanDto> fetchLoan(@PathVariable String mobileNumber);
}
