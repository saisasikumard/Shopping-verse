package com.example.shopping_verse.exceptions;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException(String msg){
        super(msg);
    }
}
