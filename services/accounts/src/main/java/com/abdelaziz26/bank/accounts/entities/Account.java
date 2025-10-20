package com.abdelaziz26.bank.accounts.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity @Table(name = "accounts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountNumber;

    @Column(name="account_type")
    @NotBlank
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;

    @Positive
    @NotNull
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "toAccount")
    private List<Transaction> inwardTransactions;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transaction> outwardTransactions;

}
