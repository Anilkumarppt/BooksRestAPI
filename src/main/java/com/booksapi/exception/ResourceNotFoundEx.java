package com.booksapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceNotFoundEx extends RuntimeException {
    private String message;
    private HttpStatus status;

    public ResourceNotFoundEx(String message, HttpStatus status) {
        super();
        this.message = message;
        this.status = status;
    }
}
