package com.auth.server.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    
	private static final long serialVersionUID = 1L;

	public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
