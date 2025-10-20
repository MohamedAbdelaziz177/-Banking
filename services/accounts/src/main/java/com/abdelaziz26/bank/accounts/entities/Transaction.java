package com.abdelaziz26.bank.accounts.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Positive
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = true)
    private Account toAccount;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = true)
    private Account fromAccount;
}
