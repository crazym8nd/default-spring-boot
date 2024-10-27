package com.spring.crud.exception;

public class StudentNotFoundException extends RuntimeException {
    private final String path;

    public StudentNotFoundException(final String message, final String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}