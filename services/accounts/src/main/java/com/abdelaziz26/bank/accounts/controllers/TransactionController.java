package com.abdelaziz26.bank.accounts.controllers;

import com.abdelaziz26.bank.accounts.dto.TransactionDto;
import com.abdelaziz26.bank.accounts.entities.TransactionType;
import com.abdelaziz26.bank.accounts.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(@PathVariable Long accountId)
    {
        return ResponseEntity.ok(transactionService.getAllTransactions(accountId));
    }

    @GetMapping("/inward/{accountId}")
    public ResponseEntity<List<TransactionDto>> getInwardTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getAllInwardTransactions(accountId));
    }

    @GetMapping("/outward/{accountId}")
    public ResponseEntity<List<TransactionDto>> getOutwardTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getAllOutwardTransactions(accountId));
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionType type = TransactionType.valueOf(transactionDto.getType().toUpperCase());

        BigDecimal finalBalance = BigDecimal.valueOf(0);

        switch (type){
            case DEPOSIT -> finalBalance = transactionService.deposit(transactionDto);
            case WITHDRAWAL -> finalBalance = transactionService.withdraw(transactionDto);
            case TRANSFER -> finalBalance = transactionService.transfer(transactionDto);
        }

        return ResponseEntity
                .ok("Transaction Created Successfully! Your current balance is: " + finalBalance.toString());
    }

}
