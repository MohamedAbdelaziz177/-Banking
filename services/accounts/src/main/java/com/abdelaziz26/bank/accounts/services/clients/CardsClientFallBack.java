package com.abdelaziz26.bank.accounts.services.clients;

import com.abdelaziz26.bank.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;

public class CardsClientFallBack implements CardsClient {
    @Override
    public ResponseEntity<CardDto> fetchCard(String mobileNumber) {
        return null;
    }
}
