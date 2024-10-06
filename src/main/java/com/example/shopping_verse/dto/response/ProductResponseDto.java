package com.example.shopping_verse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;
    int price;
    int availbleQuantity;
    String sellerName;
}
