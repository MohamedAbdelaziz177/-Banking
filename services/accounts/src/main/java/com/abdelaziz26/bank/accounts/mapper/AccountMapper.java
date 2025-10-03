package com.abdelaziz26.bank.accounts.mapper;

import com.abdelaziz26.bank.accounts.dto.AccountDto;
import com.abdelaziz26.bank.accounts.entities.Account;

public class AccountMapper {

    public static Account toEntity(AccountDto dto)
    {
        Account account = new Account();

        account.setAccountNumber(dto.getAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setBranchAddress(dto.getBranchAddress());

        return account;
    }

    public static AccountDto toDto(Account account)
    {
        return AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .build();
    }
}
