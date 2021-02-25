package com.auth.server.service;

import com.auth.server.pojo.LoginRequest;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LoginService {
    String login(LoginRequest loginRequest) throws IOException, URISyntaxException;
}
