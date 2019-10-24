package com.example.testsecurity.Utils;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private T data;
    private boolean success = true;

    public Result() {
    }

    public Result(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
