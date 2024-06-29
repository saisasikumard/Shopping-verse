package com.example.shopping_verse.dto.response;
import com.example.shopping_verse.Enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {
    String name;
    int age;
    String email;
    String address;
    String mobile;
    Gender gender;

}
