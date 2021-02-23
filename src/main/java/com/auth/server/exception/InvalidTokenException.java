package com.auth.server.exception;

import static java.lang.String.format;

public class InvalidTokenException extends RuntimeException {

	 public InvalidTokenException(String username) {
	        super(format("Token Invalid for username %s", username));
	    }
}
