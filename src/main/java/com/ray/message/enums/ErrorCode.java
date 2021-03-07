package com.ray.message.enums;

public enum ErrorCode {

    USERNAME_ALREADY_EXISTS(-1, "username already exists"),
    WRONG_PASSWORD(-2, "wrong password"),
    USERNAME_UN_EXISTS(-3, "invalid username"),
    INVALID_TOKEN(-4, "invalid token"),
    TOKEN_EXPIRED(-5, "token has expired");
    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
