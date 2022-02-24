package com.turgaydede.exceptions;

import com.turgaydede.util.result.ErrorResult;
import com.turgaydede.util.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(CreditNotFoundException.class)
    public ResponseEntity<Result> CustomerNotFoundException(CreditNotFoundException exception) {
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(message));
    }
}