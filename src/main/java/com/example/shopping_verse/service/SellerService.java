package com.example.shopping_verse.service;

import com.example.shopping_verse.dto.request.SellerRequestDto;
import com.example.shopping_verse.dto.response.SellerResponseDto;
import com.example.shopping_verse.entity.Seller;
import com.example.shopping_verse.repository.SellerRepository;
import com.example.shopping_verse.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){
        Seller seller= SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller=sellerRepository.save(seller);
        SellerResponseDto sellerResponseDto=SellerTransformer.sellerToSellerResponseDto(savedSeller);

        return sellerResponseDto;
    }
}
