package com.abdelaziz26.bank.accounts.services;

import com.abdelaziz26.bank.accounts.dto.AccountDto;
import com.abdelaziz26.bank.accounts.dto.TransactionDto;
import com.abdelaziz26.bank.accounts.entities.Account;
import com.abdelaziz26.bank.accounts.entities.Transaction;
import com.abdelaziz26.bank.accounts.entities.TransactionType;
import com.abdelaziz26.bank.accounts.exceptions.ResourceNotFoundException;
import com.abdelaziz26.bank.accounts.mapper.TransactionMapper;
import com.abdelaziz26.bank.accounts.repositories.AccountRepository;
import com.abdelaziz26.bank.accounts.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionDto getTransactionById(Long id) {
        Transaction trx = transactionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Transaction", "Id", id.toString()));
        return TransactionMapper.toDto(trx);
    }

    public List<TransactionDto> getAllTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream().map(TransactionMapper::toDto).toList();
    }

    public List<TransactionDto> getAllInwardTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.findInwardTransactions(accountId);
        return transactions.stream().map(TransactionMapper::toDto).toList();
    }

    public List<TransactionDto> getAllOutwardTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.findOutwardTransactions(accountId);
        return transactions.stream().map(TransactionMapper::toDto).toList();
    }

    @Transactional
    public BigDecimal deposit(TransactionDto transactionDto) {
        Account acc = accountRepository.findById(transactionDto.getToAccountId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", transactionDto.getToAccountId().toString()));
        Transaction trx = new Transaction();
        trx.setType(TransactionType.DEPOSIT);
        trx.setAmount(transactionDto.getAmount());
        trx.setToAccount(acc);
        trx.setDate(LocalDateTime.now());

        acc.setBalance(acc.getBalance().add(transactionDto.getAmount()));
        accountRepository.save(acc);
        transactionRepository.save(trx);

        return acc.getBalance();
    }

    @Transactional
    public BigDecimal withdraw(TransactionDto transactionDto) {
        Account acc = accountRepository.findById(transactionDto.getFromAccountId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", transactionDto.getFromAccountId().toString()));

        if(acc.getBalance().compareTo(transactionDto.getAmount()) < 0)
            throw new IllegalArgumentException("Insufficient balance");

        Transaction trx = new Transaction();
        trx.setType(TransactionType.WITHDRAWAL);
        trx.setAmount(transactionDto.getAmount());
        trx.setFromAccount(acc);
        trx.setDate(LocalDateTime.now());

        acc.setBalance(acc.getBalance().subtract(transactionDto.getAmount()));
        accountRepository.save(acc);
        transactionRepository.save(trx);

        return acc.getBalance();
    }

    @Transactional
    public BigDecimal transfer(TransactionDto transactionDto) {
        Account from = accountRepository.findById(transactionDto.getFromAccountId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", transactionDto.getFromAccountId().toString()));

        Account to = accountRepository.findById(transactionDto.getToAccountId()).orElseThrow(() ->
                new ResourceNotFoundException("Account", "Id", transactionDto.getToAccountId().toString()));

        if(from.getBalance().compareTo(transactionDto.getAmount()) < 0)
            throw new IllegalArgumentException("Insufficient balance");

        from.setBalance(from.getBalance().subtract(transactionDto.getAmount()));
        to.setBalance(to.getBalance().add(transactionDto.getAmount()));

        Transaction trx = new Transaction();
        trx.setType(TransactionType.TRANSFER);
        trx.setAmount(transactionDto.getAmount());
        trx.setFromAccount(from);
        trx.setToAccount(to);
        trx.setDate(LocalDateTime.now());

        accountRepository.save(from);
        accountRepository.save(to);
        transactionRepository.save(trx);

        return from.getBalance();
    }

}
