package com.samfrosh.exception;

public class UserExits extends Exception{

    public UserExits() {
        super();
    }

    public UserExits(String message) {
        super(message);
    }

    public UserExits(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExits(Throwable cause) {
        super(cause);
    }

    protected UserExits(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
