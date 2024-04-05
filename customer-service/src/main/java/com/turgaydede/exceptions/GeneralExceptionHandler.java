package com.turgaydede.exceptions;

import com.turgaydede.util.result.ErrorResult;
import com.turgaydede.util.result.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Log4j2
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    validationErrors.put(fieldName, errorMessage);
                });
        log.error(validationErrors);
        return ResponseEntity.badRequest().body(new ErrorResult(validationErrors));
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Result> CustomerNotFoundException(CustomerNotFoundException exception) {
        String message = exception.getMessage();
        log.error(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(message));
    }
}