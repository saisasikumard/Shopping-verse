package com.example.shopping_verse.exceptions;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String msg){
        super(msg);
    }
}
