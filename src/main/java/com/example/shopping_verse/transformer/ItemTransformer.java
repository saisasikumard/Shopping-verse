package com.example.shopping_verse.transformer;

import com.example.shopping_verse.dto.request.ItemRequestDto;
import com.example.shopping_verse.dto.response.ItemResponseDto;
import com.example.shopping_verse.entity.Item;

public class ItemTransformer {
    public static Item itemRequestDtoTOItem(ItemRequestDto itemRequestDto){
        return Item.builder().reqQuantity(itemRequestDto.getReqQuantity())
                .build();
    }
    public static ItemResponseDto itemToItemResponseDto(Item item){
        return ItemResponseDto.builder().
                itemName(item.getProduct().getProductName())
                .itemPrice(item.getProduct().getPrice())
                .quantityAdded(item.getReqQuantity())
                .category(item.getProduct().getCategory())
                .build();
    }
}
