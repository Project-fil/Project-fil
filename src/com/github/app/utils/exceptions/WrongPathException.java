package com.github.app.utils.exceptions;

public class WrongPathException extends IllegalArgumentException {
    public WrongPathException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public WrongPathException(String message){
        super(message);
    }

}