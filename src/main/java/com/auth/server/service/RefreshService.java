package com.auth.server.service;

import com.auth.server.pojo.RefreshTokenRequest;

public interface RefreshService {
    String refreshToken(RefreshTokenRequest refreshTokenRequest);
}
