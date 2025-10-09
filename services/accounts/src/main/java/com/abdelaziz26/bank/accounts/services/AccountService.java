package com.abdelaziz26.bank.accounts.services;

import com.abdelaziz26.bank.accounts.common.AccountsConstants;
import com.abdelaziz26.bank.accounts.dto.AccountDto;
import com.abdelaziz26.bank.accounts.dto.CustomerDto;
import com.abdelaziz26.bank.accounts.entities.Account;
import com.abdelaziz26.bank.accounts.entities.Customer;
import com.abdelaziz26.bank.accounts.exceptions.CustomerAlreadyExistsException;
import com.abdelaziz26.bank.accounts.exceptions.ResourceNotFoundException;
import com.abdelaziz26.bank.accounts.mapper.AccountMapper;
import com.abdelaziz26.bank.accounts.mapper.CustomerMapper;
import com.abdelaziz26.bank.accounts.repositories.AccountRepository;
import com.abdelaziz26.bank.accounts.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByPhone(customerDto.getPhone());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getPhone());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByPhone(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.toDto(customer);
        customerDto.setAccountDto(AccountMapper.toDto(account));
        return customerDto;
    }


    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Account account = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            Account updatedAccount = AccountMapper.toEntity(accountsDto);
            updatedAccount.setCustomerId(account.getCustomerId());
            accountsRepository.save(updatedAccount);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            Customer updatedCustomer =  CustomerMapper.toEntity(customerDto);
            updatedCustomer.setCustomerId(customer.getCustomerId());
            customerRepository.save(customer);

            isUpdated = true;
        }
        return isUpdated;
    }


    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByPhone(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
