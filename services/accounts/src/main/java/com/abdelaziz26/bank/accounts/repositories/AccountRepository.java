package com.abdelaziz26.bank.accounts.repositories;

import com.abdelaziz26.bank.accounts.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomer_Id(Long customerId);

    @Transactional
    @Modifying
    void deleteByCustomer_Id(Long customerId);
}
