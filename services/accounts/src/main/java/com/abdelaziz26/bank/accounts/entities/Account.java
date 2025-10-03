package com.abdelaziz26.bank.accounts.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "accounts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Account extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountNumber;

    @Column(name="account_type")
    @NotBlank
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;

    @Column(name="customer_id")
    private Long customerId;

}
