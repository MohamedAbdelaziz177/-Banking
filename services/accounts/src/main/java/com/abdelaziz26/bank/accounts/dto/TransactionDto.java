package com.abdelaziz26.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionDto {

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String type;

    private Long toAccountId;

    private Long fromAccountId;

    private LocalDateTime date = LocalDateTime.now();
}
