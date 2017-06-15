package com.zjava.exception;

/**
 * Created by Rafal Lebioda on 15.06.2017.
 */
public class EmailNotUniqueException extends Exception {

    public EmailNotUniqueException(Throwable cause) {
        super(cause);
    }

    public EmailNotUniqueException() {
        super();
    }

    public EmailNotUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmailNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotUniqueException(String message) {
        super(message);
    }
}
