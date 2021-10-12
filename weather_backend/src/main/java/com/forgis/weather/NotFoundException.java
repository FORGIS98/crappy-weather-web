package com.forgis.weather;

public class NotFoundException extends Throwable {

    String code;
    String message;

    public NotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
