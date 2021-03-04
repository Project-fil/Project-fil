package com.github.app.utils.exceptions;

public class DifferentArraySizesException extends RuntimeException{
    public DifferentArraySizesException(String errorMessage){
        super(errorMessage);
    }
}
