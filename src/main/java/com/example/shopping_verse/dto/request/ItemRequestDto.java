package com.example.shopping_verse.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ItemRequestDto {
    String customerEmail;
    int productId;
    int reqQuantity;
}
