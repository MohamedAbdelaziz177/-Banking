package com.abdelaziz26.bank.notifications;

public record AccountMsgDto(
        Long accountNumber,
        String accountType,
        String email,
        String phone
){
}
