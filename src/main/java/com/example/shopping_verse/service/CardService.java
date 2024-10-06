package com.example.shopping_verse.service;

import com.example.shopping_verse.dto.request.CardRequestDto;
import com.example.shopping_verse.dto.response.CardResponseDto;
import com.example.shopping_verse.entity.Card;
import com.example.shopping_verse.entity.Customer;
import com.example.shopping_verse.repository.CardRepository;
import com.example.shopping_verse.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        Card card=new Card();
        card.setCardNo(cardRequestDto.getCardNo());
        card.setCardType(cardRequestDto.getCardType());
        card.setCvv(cardRequestDto.getCvv());
        card.setValidTill(cardRequestDto.getValidTill());
        Customer customer=customerRepository.findByEmail(cardRequestDto.getCustomerEmail());
        card.setCustomer(customer);

        cardRepository.save(card);
        //entity to Dto
        CardResponseDto cardResponseDto=new CardResponseDto();
        cardResponseDto.setMaskedCardNO(cardToMaasked(card.getCardNo()));
        cardResponseDto.setCardType(card.getCardType());
        cardResponseDto.setValidTill(card.getValidTill());
        return cardResponseDto;
    }

    public String cardToMaasked(String card){
        String maskedCard="";
        for(int i=0;i<card.length()-4;i++){
            maskedCard+='X';
        }
        maskedCard+=card.substring(card.length()-4);
        return maskedCard;
    }
}
