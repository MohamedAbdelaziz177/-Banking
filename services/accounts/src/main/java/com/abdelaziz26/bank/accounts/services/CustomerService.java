package com.abdelaziz26.bank.accounts.services;

import com.abdelaziz26.bank.accounts.dto.CardDto;
import com.abdelaziz26.bank.accounts.dto.CustomerDetailsDto;
import com.abdelaziz26.bank.accounts.dto.CustomerDto;
import com.abdelaziz26.bank.accounts.dto.LoanDto;
import com.abdelaziz26.bank.accounts.entities.Account;
import com.abdelaziz26.bank.accounts.entities.Customer;
import com.abdelaziz26.bank.accounts.exceptions.ResourceNotFoundException;
import com.abdelaziz26.bank.accounts.mapper.AccountMapper;
import com.abdelaziz26.bank.accounts.mapper.CustomerMapper;
import com.abdelaziz26.bank.accounts.repositories.AccountRepository;
import com.abdelaziz26.bank.accounts.repositories.CustomerRepository;
import com.abdelaziz26.bank.accounts.services.clients.CardsClient;
import com.abdelaziz26.bank.accounts.services.clients.LoansClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardsClient cardsClient;
    private final LoansClient loansClient;

    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber)
    {
        Customer customer = customerRepository.findByPhone(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.toCustomerDetailsDto(customer);
        customerDetailsDto.setAccount(AccountMapper.toDto(account));

        ResponseEntity<LoanDto> loanResponse = loansClient.fetchLoan(mobileNumber);
        customerDetailsDto.setLoan(loanResponse.getBody());

        ResponseEntity<CardDto> cardResponse = cardsClient.fetchCard(mobileNumber);
        customerDetailsDto.setCard(cardResponse.getBody());

        return customerDetailsDto;

    }

}
