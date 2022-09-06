package com.booksapi.exception;

import com.booksapi.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //method to  handle exception

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse> handleRuntimeException(RuntimeException e) {

        String message = e.getMessage();

        APIResponse response = new APIResponse();
        response.setMessage(message);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setSuccess(false);
        return new ResponseEntity<APIResponse>(response, HttpStatus.BAD_REQUEST);


    }


    //method to  handle exception

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<APIResponse> handleSQLException(SQLException e) {

        String message = e.getMessage();
        APIResponse response = new APIResponse();
        response.setMessage(message);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setSuccess(false);
        return new ResponseEntity<APIResponse>(response, HttpStatus.BAD_REQUEST);


    }
}
