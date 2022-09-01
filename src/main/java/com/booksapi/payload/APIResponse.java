package com.booksapi.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class APIResponse {

    private String message;
    private HttpStatus status;
    private boolean success;

}
