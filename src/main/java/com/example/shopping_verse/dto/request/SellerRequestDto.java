package com.example.shopping_verse.dto.request;

import com.example.shopping_verse.Enums.Gender;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class SellerRequestDto {
    String name;
    String pan;
    int age;
    String email;
    String mobile;
}
