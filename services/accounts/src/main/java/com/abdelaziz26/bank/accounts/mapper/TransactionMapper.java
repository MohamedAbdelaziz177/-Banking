package com.abdelaziz26.bank.accounts.mapper;

import com.abdelaziz26.bank.accounts.dto.TransactionDto;
import com.abdelaziz26.bank.accounts.entities.Account;
import com.abdelaziz26.bank.accounts.entities.Transaction;
import com.abdelaziz26.bank.accounts.entities.TransactionType;

public class TransactionMapper {

    public static Transaction toEntity(TransactionDto dto, Account toAcc, Account fromAcc)
    {
        Transaction transaction = new Transaction();

        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setType(TransactionType.valueOf(dto.getType().toUpperCase()));

        if(transaction.getType() == TransactionType.DEPOSIT)
            transaction.setToAccount(toAcc);
        else if(transaction.getType() == TransactionType.WITHDRAWAL)
            transaction.setFromAccount(fromAcc);
        else {
            transaction.setToAccount(toAcc);
            transaction.setFromAccount(fromAcc);
        }

        return transaction;
    }

    public static TransactionDto toDto(Transaction transaction)
    {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setType(transaction.getType().toString());

        if(transaction.getType() == TransactionType.DEPOSIT)
            transactionDto.setToAccountId(transactionDto.getToAccountId());
        else if(transaction.getType() == TransactionType.WITHDRAWAL)
            transactionDto.setFromAccountId(transactionDto.getFromAccountId());
        else {
            transactionDto.setToAccountId(transactionDto.getToAccountId());
            transactionDto.setFromAccountId(transactionDto.getFromAccountId());
        }

        return transactionDto;
    }
}
