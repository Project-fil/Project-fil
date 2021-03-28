package org.bitbucket.app.exceptions;

public class WrongDBException extends IllegalArgumentException {

    public WrongDBException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public WrongDBException(String message) {
        super(message);
    }
}
