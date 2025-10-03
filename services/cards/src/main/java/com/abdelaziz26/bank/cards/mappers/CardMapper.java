package com.abdelaziz26.bank.cards.mappers;

import com.abdelaziz26.bank.cards.dto.CardDto;
import com.abdelaziz26.bank.cards.entities.Card;

public class CardMapper {

    public static Card toEntity(CardDto cardDto) {

        Card card = new Card();
        card.setCardNumber(String.valueOf(cardDto.getCardNumber()));
        card.setCardType(cardDto.getCardType());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setAmountUsed(cardDto.getAmountUsed());
        return card;
    }

    public static CardDto toDto(Card card){

        return CardDto.builder()
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .availableAmount(card.getAvailableAmount())
                .mobileNumber(card.getMobileNumber())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .build();
    }
}
