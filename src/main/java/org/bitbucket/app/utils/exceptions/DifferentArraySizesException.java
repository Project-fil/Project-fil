package org.bitbucket.app.utils.exceptions;

public class DifferentArraySizesException extends RuntimeException{
    public DifferentArraySizesException(String errorMessage){
        super(errorMessage);
    }
}
