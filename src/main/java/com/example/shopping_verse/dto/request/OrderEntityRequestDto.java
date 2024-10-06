package com.example.shopping_verse.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderEntityRequestDto {
    String custmerEmail;
    String cardUsed;
    int cvv;
    int productId;
    int requiredQuantity;
}
