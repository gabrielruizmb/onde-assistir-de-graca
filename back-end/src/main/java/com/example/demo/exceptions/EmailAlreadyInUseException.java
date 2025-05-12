package com.example.demo.exceptions;

public class EmailAlreadyInUseException extends Exception{
    
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
