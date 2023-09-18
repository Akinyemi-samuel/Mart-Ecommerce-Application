package com.samfrosh.exception;

public class CartError extends Exception {
    public CartError() {
        super();
    }

    public CartError(String message) {
        super(message);
    }

    public CartError(String message, Throwable cause) {
        super(message, cause);
    }

    public CartError(Throwable cause) {
        super(cause);
    }

    protected CartError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
