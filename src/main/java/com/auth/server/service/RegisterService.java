package com.auth.server.service;

import com.auth.server.exception.ConfirmedPasswordNotEqualsException;
import com.auth.server.exception.UserAlreadyExistException;
import com.auth.server.pojo.RegisterRequest;

public interface RegisterService {
    Boolean register(RegisterRequest registerRequest) throws ConfirmedPasswordNotEqualsException, UserAlreadyExistException;
}
