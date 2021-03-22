package org.bitbucket.app.exceptions;

public class DifferentArraySizesException extends RuntimeException{
    public DifferentArraySizesException(String errorMessage){
        super(errorMessage);
    }
}
