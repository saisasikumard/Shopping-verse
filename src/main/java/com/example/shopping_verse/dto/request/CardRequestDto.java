package com.example.shopping_verse.dto.request;

import com.example.shopping_verse.Enums.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CardRequestDto {
    String cardNo;
    int cvv;
    Date validTill;
    CardType cardType;
    String customerEmail;

}
