package com.abdelaziz26.bank.cards.controllers;

import com.abdelaziz26.bank.cards.dto.CardDto;
import com.abdelaziz26.bank.cards.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/{mobileNumber}")
    public ResponseEntity<String> createCard(@PathVariable String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Card created successfully for mobile number: " + mobileNumber);
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CardDto> fetchCard(@PathVariable String mobileNumber) {
        CardDto cardDto = cardService.fetchCard(mobileNumber);
        return ResponseEntity.ok(cardDto);
    }

    @PutMapping
    public ResponseEntity<String> updateCard(@RequestBody CardDto cardDto) {
        boolean updated = cardService.updateCard(cardDto);
        if (updated) {
            return ResponseEntity.ok("Card updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update card.");
        }
    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<String> deleteCard(@PathVariable String mobileNumber) {
        boolean deleted = cardService.deleteCard(mobileNumber);
        if (deleted) {
            return ResponseEntity.ok("Card deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete card.");
        }
    }
}
