package com.example.demo.features.user;

public class EmailAlreadyInUseException extends Exception{
    
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
