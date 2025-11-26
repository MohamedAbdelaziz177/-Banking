package com.abdelaziz26.bank.loans.controllers;

import com.abdelaziz26.bank.loans.dtos.LoanDto;
import com.abdelaziz26.bank.loans.dtos.LoanPaymentRequest;
import com.abdelaziz26.bank.loans.dtos.LoanPaymentResponse;
import com.abdelaziz26.bank.loans.services.LoanService;
import com.abdelaziz26.bank.loans.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final PaymentService paymentService;

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
        //throw new RuntimeException("interrupt");
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

    @PostMapping("/pay-loan")
    public ResponseEntity<LoanPaymentResponse> addLoanPayment(@RequestBody LoanPaymentRequest loanPaymentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.pay(loanPaymentRequest));
    }

    @GetMapping("/payment-transactions")
    public ResponseEntity<List<LoanPaymentResponse>> fetchPaymentTransactions(@RequestParam Long loanId) {
        return ResponseEntity.ok(paymentService.findAllByLoanId(loanId));
    }
}
