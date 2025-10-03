package com.abdelaziz26.bank.accounts.services.clients;

import com.abdelaziz26.bank.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cards")
public interface CardsClient {
    @GetMapping("/{mobileNumber}")
    ResponseEntity<CardDto> fetchCard(@PathVariable String mobileNumber);
}
