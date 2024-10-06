package com.example.shopping_verse.transformer;

import com.example.shopping_verse.dto.response.ItemResponseDto;
import com.example.shopping_verse.dto.response.OrderEntityResponseDto;
import com.example.shopping_verse.entity.Item;
import com.example.shopping_verse.entity.OrderEntity;
import com.example.shopping_verse.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderEntityTransformer {

    public static OrderEntityResponseDto orderToOrderEntityResponseDto(OrderEntity orderEntity,String maskedCard){
        List<ItemResponseDto> items=new ArrayList<>();
        for(Item item:orderEntity.getItemList()){
            items.add(ItemTransformer.itemToItemResponseDto(item));
        }
        return OrderEntityResponseDto.builder()
                .customerName(orderEntity.getCustomer().getName())
                .orderId(orderEntity.getOrderId())
                .date(orderEntity.getDate())
                .items(items)
                .maskedCard(maskedCard)
                .build();
    }
}
