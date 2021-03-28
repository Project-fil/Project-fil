package org.bitbucket.app.exceptions;

public class NoSuchDatabaseException extends IllegalArgumentException{
    public NoSuchDatabaseException(String message){
        super(message);
    }
}
