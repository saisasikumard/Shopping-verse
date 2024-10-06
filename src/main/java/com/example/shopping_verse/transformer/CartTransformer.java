package com.example.shopping_verse.transformer;

import com.example.shopping_verse.dto.response.CartResponseDto;
import com.example.shopping_verse.dto.response.ItemResponseDto;
import com.example.shopping_verse.entity.Cart;
import com.example.shopping_verse.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto cartToCartResponse(Cart cart){
        List<ItemResponseDto> items=new ArrayList<>();
        for(Item item:cart.getItemList()){
            ItemResponseDto itemResponseDto=ItemTransformer.itemToItemResponseDto(item);
            items.add(itemResponseDto);
        }
        CartResponseDto cartResponseDto=CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartToatal(cart.getCartTotal())
                .items(items)
                .build();
        cartResponseDto.getCartToatal();
        return cartResponseDto;



    }
}
