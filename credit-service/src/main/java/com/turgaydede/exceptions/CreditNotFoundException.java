package com.turgaydede.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Kredi bulunamadı";

    public CreditNotFoundException() {
        super(MESSAGE);
    }
}
