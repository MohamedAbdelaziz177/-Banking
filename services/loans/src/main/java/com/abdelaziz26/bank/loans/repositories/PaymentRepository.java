package com.abdelaziz26.bank.loans.repositories;

import com.abdelaziz26.bank.loans.entities.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<LoanPayment, Long> {
    List<LoanPayment> findAllByLoan_LoanId(Long loanId);
}
