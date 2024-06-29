package com.example.shopping_verse.transformer;

import com.example.shopping_verse.dto.request.SellerRequestDto;
import com.example.shopping_verse.dto.response.SellerResponseDto;
import com.example.shopping_verse.entity.Seller;

public class SellerTransformer
{
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .pan(sellerRequestDto.getPan())
                .email(sellerRequestDto.getEmail())
                .mobile(sellerRequestDto.getMobile())
                .build();
    }
    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .mobile(seller.getMobile())
                .build();
    }
}
