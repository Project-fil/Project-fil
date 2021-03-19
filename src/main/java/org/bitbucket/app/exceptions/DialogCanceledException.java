package org.bitbucket.app.exceptions;

public class DialogCanceledException extends RuntimeException{
    public DialogCanceledException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public DialogCanceledException(String errorMessage) {
        super(errorMessage);
    }

    public DialogCanceledException() {
        super();
    }

}
