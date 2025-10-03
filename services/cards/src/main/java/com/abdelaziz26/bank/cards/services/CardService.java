package com.abdelaziz26.bank.cards.services;

import com.abdelaziz26.bank.cards.common.CardsConstants;
import com.abdelaziz26.bank.cards.dto.CardDto;
import com.abdelaziz26.bank.cards.entities.Card;
import com.abdelaziz26.bank.cards.exceptions.CardAlreadyExistsException;
import com.abdelaziz26.bank.cards.exceptions.ResourceNotFoundException;
import com.abdelaziz26.bank.cards.mappers.CardMapper;
import com.abdelaziz26.bank.cards.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardsRepository;

    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }


    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    public CardDto fetchCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.toDto(card);
    }

    public boolean updateCard(CardDto cardDto) {
        Card card = cardsRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardDto.getCardNumber()));
        CardMapper.toEntity(cardDto);
        cardsRepository.save(card);
        return  true;
    }


    public boolean deleteCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(card.getCardId());
        return true;
    }
}
