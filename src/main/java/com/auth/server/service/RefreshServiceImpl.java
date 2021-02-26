package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.pojo.RefreshTokenRequest;
import com.auth.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RefreshServiceImpl implements RefreshService {

    private final UserService userService;

    @Override
    public String refreshToken(RefreshTokenRequest refreshTokenRequest) {

        User user = (User) userService.loadUserByUsername(refreshTokenRequest.getUserName());
        return null;
    }
}
