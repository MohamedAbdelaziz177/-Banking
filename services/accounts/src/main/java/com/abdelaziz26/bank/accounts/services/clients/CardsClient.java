package com.abdelaziz26.bank.accounts.services.clients;

import com.abdelaziz26.bank.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cards", url = "${microservices.cards.url}", fallback = CardsClientFallBack.class)
public interface CardsClient {
    @GetMapping("/{mobileNumber}")
    ResponseEntity<CardDto> fetchCard(@PathVariable String mobileNumber);
}
