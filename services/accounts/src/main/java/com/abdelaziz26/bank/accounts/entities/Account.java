package com.abdelaziz26.bank.accounts.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
