package com.example.shopping_verse.dto.response;

import com.example.shopping_verse.Enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String maskedCardNO;
    Date validTill;
    CardType cardType;

}
