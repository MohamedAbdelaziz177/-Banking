package com.abdelaziz26.bank.accounts.repositories;

import com.abdelaziz26.bank.accounts.entities.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
           SELECT t FROM Transaction t
           WHERE t.fromAccount.accountNumber = :accountId
           OR t.toAccount.accountNumber = :accountId
           """)
    List<Transaction> findByAccountId(@Param("accountId") Long accountId);

    @Query("""
          SELECT t FROM Transaction t
          WHERE t.toAccount.accountNumber = :accountId
          """)
    List<Transaction> findInwardTransactions(@Param("accountId") Long accountId);

    @Query("""
          SELECT t FROM Transaction t
          WHERE t.fromAccount.accountNumber = :accountId
          """)
    List<Transaction> findOutwardTransactions(@Param("accountId") Long accountId);

    List<Transaction> findAll(Specification<Transaction> spec);
}
