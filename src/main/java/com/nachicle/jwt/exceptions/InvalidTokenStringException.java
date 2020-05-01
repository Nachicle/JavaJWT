package com.nachicle.jwt.exceptions;

public class InvalidTokenStringException extends Exception {
    public InvalidTokenStringException(String message){
        super(message);
    }
}
