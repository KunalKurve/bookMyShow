package com.scaler.bookMyShow.exceptions;

public class ShowDoesNotExistException extends RuntimeException{

    public ShowDoesNotExistException(String message){
        super(message);
    }
}
