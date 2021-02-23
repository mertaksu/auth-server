package com.auth.server.exception;

public class ConfirmedPasswordNotEqualsException extends Throwable {
    public ConfirmedPasswordNotEqualsException(String confirmed_password_not_equals) {
        super(confirmed_password_not_equals);
    }
}
