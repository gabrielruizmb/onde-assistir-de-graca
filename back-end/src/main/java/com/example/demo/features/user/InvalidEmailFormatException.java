package com.example.demo.features.user;

public class InvalidEmailFormatException extends Exception{
    
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
