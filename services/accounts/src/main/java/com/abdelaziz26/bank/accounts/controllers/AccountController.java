package com.abdelaziz26.bank.accounts.controllers;

import com.abdelaziz26.bank.accounts.dto.CustomerDto;
import com.abdelaziz26.bank.accounts.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/testCircuitBreaker")
    public ResponseEntity<?> testCircuitBreaker() {
        throw new RuntimeException("testFaultTolerance");
    }

    @GetMapping("/testRetry")
    public ResponseEntity<?> testRetry() {
        logger.info("I should be shown 3 times..\uD83D\uDC85");
        throw new RuntimeException("testRetry");
    }

    @GetMapping("/testTimeout")
    public ResponseEntity<?> testTimeout(){

        logger.debug("put breakpoint here");
        return ResponseEntity.ok("I WILL BE SHOWN IF YOU REMOVE BREAKPOINT ONLY");
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Account created successfully for customer with mobile: " + customerDto.getPhone());
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDto> fetchAccount(@PathVariable String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping
    public ResponseEntity<String> updateAccount(@RequestBody CustomerDto customerDto) {
        boolean updated = accountService.updateAccount(customerDto);
        if (updated) {
            return ResponseEntity.ok("Account updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No account details provided to update.");
        }
    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String mobileNumber) {
        boolean deleted = accountService.deleteAccount(mobileNumber);
        if (deleted) {
            return ResponseEntity.ok("Account deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete account.");
        }
    }
}
