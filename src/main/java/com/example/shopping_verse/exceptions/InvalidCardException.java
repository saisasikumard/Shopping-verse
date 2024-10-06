package com.example.shopping_verse.exceptions;

public class InvalidCardException extends RuntimeException{
    public InvalidCardException(String msg){
        super(msg);
    }
}
