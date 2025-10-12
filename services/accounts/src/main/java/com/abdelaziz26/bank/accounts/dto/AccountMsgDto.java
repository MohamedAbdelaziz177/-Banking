package com.abdelaziz26.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

public record AccountMsgDto(
        Long accountNumber,
        String accountType,
        String email,
        String phone
){
}
