package com.darknights.devigation.global.exception;

public class ResourceNotFoundException extends NullPointerException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
