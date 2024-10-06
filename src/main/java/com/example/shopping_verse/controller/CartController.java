package com.example.shopping_verse.controller;

import com.example.shopping_verse.dto.request.CheckoutCartRequestDto;
import com.example.shopping_verse.dto.request.ItemRequestDto;
import com.example.shopping_verse.dto.response.CartResponseDto;
import com.example.shopping_verse.dto.response.OrderEntityResponseDto;
import com.example.shopping_verse.entity.Item;
import com.example.shopping_verse.service.CartService;
import com.example.shopping_verse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;


    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
            Item item=itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto=cartService.addToCart(itemRequestDto,item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public ResponseEntity checkout(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){
        try{
            OrderEntityResponseDto orderEntityResponseDto=cartService.checkoutCart(checkoutCartRequestDto);
            return new ResponseEntity(orderEntityResponseDto,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
