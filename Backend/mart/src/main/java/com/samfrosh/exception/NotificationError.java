package com.samfrosh.exception;

public class NotificationError extends Exception{

    public NotificationError() {
        super();
    }

    public NotificationError(String message) {
        super(message);
    }

    public NotificationError(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationError(Throwable cause) {
        super(cause);
    }

    protected NotificationError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
