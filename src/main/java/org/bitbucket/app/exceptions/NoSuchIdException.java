package org.bitbucket.app.exceptions;

public class NoSuchIdException extends IllegalArgumentException{
    public NoSuchIdException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public NoSuchIdException(String errorMessage) {
        super(errorMessage);
    }
}