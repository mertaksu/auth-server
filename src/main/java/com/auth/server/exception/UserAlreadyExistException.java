package com.auth.server.exception;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String s) {
        super(s);
    }
}
