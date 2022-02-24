package com.turgaydede.util.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDataResult<T> extends DataResult<T> {
    private Map<String,String> validationErrors;

    public ErrorDataResult(String message, T data, Map<String,String> validationErrors) {
        super(false, data, message);
        this.validationErrors = validationErrors;
    }
    public ErrorDataResult(String message, T data) {
        super(false, data, message);
    }

    public ErrorDataResult(T data) {
        super(false, data);
    }

    public ErrorDataResult(String message) {
        super(false, null, message);
    }

    public ErrorDataResult() {
        super(false, null);
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}