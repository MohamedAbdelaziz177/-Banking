package com.abdelaziz26.bank.loans.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private Double totalLoan;

    private Double amountPaid;

    private Double outstandingAmount;
}
