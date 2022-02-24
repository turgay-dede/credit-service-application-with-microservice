package com.turgaydede.util.result;

public class SuccessDataResult<T> extends DataResult<T>{

    public SuccessDataResult(T data,String message) {
        super(true, data, message);
    }

    public SuccessDataResult(T data) {
        super(true, data);
    }

    public SuccessDataResult(String message) {
        super(true, null, message);
    }

    public SuccessDataResult() {
        super(true, null);
    }
}