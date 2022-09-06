package com.booksapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorPayload {
    private String errorMsg;
    private HttpStatus status;
}
