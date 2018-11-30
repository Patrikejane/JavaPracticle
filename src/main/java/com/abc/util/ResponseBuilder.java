package com.abc.util;

/**
 * Created by SunimalM on 11/30/2018.
 */
public class ResponseBuilder<T> {

    private String status;
    private int statusCode;
    private String message;
    private T payload;

    public ResponseBuilder() {
    }

    public ResponseBuilder(String status, int statuscode, String message, T payload) {
        this.status = status;
        this.statusCode = statuscode;
        this.message = message;
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
