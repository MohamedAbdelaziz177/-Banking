package com.abdelaziz26.bank.loans.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class LoanPaymentRequest {

    @Positive
    @NotNull
    private Double amount;

    @NotBlank
    private String mobile;

    private LocalDateTime timestamp;

    @NotNull
    private Long loanId;

}
