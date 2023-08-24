package com.darknights.devigation.common.exception;

public class ResourceNotFoundException extends NullPointerException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
