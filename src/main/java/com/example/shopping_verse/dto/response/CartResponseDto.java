package com.example.shopping_verse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    String customerName;
    int cartToatal;
    List<ItemResponseDto> items;
}
