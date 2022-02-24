package com.turgaydede.util.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult extends Result {
    private Map<String,String> validationErrors;

    public ErrorResult(String message, Map<String, String> validationErrors) {
        super(false, message);
        this.validationErrors = validationErrors;
    }

    public ErrorResult(String message) {
        super(false, message);
    }

    public ErrorResult() {
        super(false);
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}