package com.example.shopping_verse.dto.request;

import com.example.shopping_verse.Enums.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class CustomerRequestDto {
    String name;
    int age;
    String email;
    String address;
    String mobile;
    Gender gender;
}
