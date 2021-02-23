package com.auth.server.exception;

import org.springframework.security.core.AuthenticationException;

import static java.lang.String.format;

public class FailedToLoginException extends AuthenticationException {
    public FailedToLoginException(String username) {
        super(format("Failed to login with username %s", username));
    }
}
