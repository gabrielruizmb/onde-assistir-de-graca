package com.example.demo.exceptions;

public class InvalidEmailFormatException extends Exception{
    
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
