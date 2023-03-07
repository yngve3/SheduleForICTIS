package com.example.sheduleforictis.utils;

public abstract class ResultStatus<T> {
    protected T data = null;
    protected String message = null;
    protected boolean isSuccess;

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean dataIsNull() {
        return data == null;
    }

    public static final class Success<T> extends ResultStatus<T> {
        public Success(T data) {
            super.data = data;
            super.isSuccess = true;
        }
    }

    public static final class Error<T> extends ResultStatus<T> {
        public Error(String errorMessage) {
            super.message = errorMessage;
            super.isSuccess = false;
        }
    }
}
