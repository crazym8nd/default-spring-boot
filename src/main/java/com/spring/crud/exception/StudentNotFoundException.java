package com.spring.crud.exception;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException {
    private final String path;

    public StudentNotFoundException(final String message, final String path) {
        super(message);
        this.path = path;
    }

}