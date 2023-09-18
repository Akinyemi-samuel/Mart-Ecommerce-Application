package com.samfrosh.exception;

public class ProductNotFound extends Exception{
    public ProductNotFound() {
        super();
    }

    public ProductNotFound(String message) {
        super(message);
    }

    public ProductNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFound(Throwable cause) {
        super(cause);
    }

    protected ProductNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
