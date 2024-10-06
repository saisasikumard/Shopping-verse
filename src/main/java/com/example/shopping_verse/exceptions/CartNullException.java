package com.example.shopping_verse.exceptions;

public class CartNullException extends RuntimeException{
    public CartNullException(String msg){
        super(msg);
    }
}
