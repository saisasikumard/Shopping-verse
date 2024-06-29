package com.example.shopping_verse.dto.response;

import com.example.shopping_verse.Enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
public class SellerResponseDto {
    String name;
    String email;
    String mobile;
}
