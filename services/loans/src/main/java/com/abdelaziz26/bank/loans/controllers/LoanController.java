package com.abdelaziz26.bank.loans.controllers;

import com.abdelaziz26.bank.loans.dtos.LoanDto;
import com.abdelaziz26.bank.loans.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/{mobileNumber}")
    public ResponseEntity<String> createLoan(@PathVariable String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Loan created successfully for mobile number: " + mobileNumber);
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<LoanDto> fetchLoan(@PathVariable String mobileNumber) {
        LoanDto loanDto = loanService.fetchLoan(mobileNumber);
        return ResponseEntity.ok(loanDto);
    }

    @PutMapping
    public ResponseEntity<String> updateLoan(@RequestBody LoanDto loanDto) {
        boolean updated = loanService.updateLoan(loanDto);
        if (updated) {
            return ResponseEntity.ok("Loan updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update loan.");
        }
    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<String> deleteLoan(@PathVariable String mobileNumber) {
        boolean deleted = loanService.deleteLoan(mobileNumber);
        if (deleted) {
            return ResponseEntity.ok("Loan deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete loan.");
        }
    }
}
