package com.example.shopping_verse.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderEntityResponseDto {
    String orderId;
    String customerName;
    Date date;
    //maskedcard have only last 4 digits
    String maskedCard;
    List<ItemResponseDto> items=new ArrayList<>();

}
