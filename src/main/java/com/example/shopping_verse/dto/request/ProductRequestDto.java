package com.example.shopping_verse.dto.request;

import com.example.shopping_verse.Enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String productName;
    ProductCategory productCategory;
    int price;
    int quanity;
    int sellerId;
}
