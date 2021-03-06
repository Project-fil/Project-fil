package org.bitbucket.app.utils.exceptions;

public class NullArgumentException extends NullPointerException{

    public NullArgumentException(String errorMessage){
        super(errorMessage);
    }
}
