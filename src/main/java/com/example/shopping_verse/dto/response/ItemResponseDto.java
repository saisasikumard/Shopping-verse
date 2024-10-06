package com.example.shopping_verse.dto.response;

import com.example.shopping_verse.Enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String itemName;
    int itemPrice;
    int quantityAdded;
    ProductCategory category;
}
