package com.spring.crud.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExceptionResponse {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponse(final Integer status, final String error, final String message, final String path) {
        this.timestamp = Instant.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
