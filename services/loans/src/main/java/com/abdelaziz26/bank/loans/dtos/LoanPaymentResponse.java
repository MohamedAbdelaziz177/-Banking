package com.abdelaziz26.bank.loans.dtos;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoanPaymentResponse {

    private Long id;

    private Double amount;

    private Double currentAmount;

    private LocalDateTime timestamp;

    private Long LoanId;

}
