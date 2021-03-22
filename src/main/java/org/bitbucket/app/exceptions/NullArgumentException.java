package org.bitbucket.app.exceptions;

public class NullArgumentException extends NullPointerException{

    public NullArgumentException(String errorMessage){
        super(errorMessage);
    }
}
