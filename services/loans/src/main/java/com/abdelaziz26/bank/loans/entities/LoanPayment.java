package com.abdelaziz26.bank.loans.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_payment")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class LoanPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Double amount;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
