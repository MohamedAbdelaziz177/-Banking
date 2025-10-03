package com.abdelaziz26.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    @NotBlank
    private Long accountNumber;

    @NotBlank
    private String accountType;

    @NotBlank
    private String branchAddress;
}
