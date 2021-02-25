package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.pojo.RefreshTokenRequest;
import com.auth.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshServiceImpl implements RefreshService {

    private final UserRepository userRepository;

    @Override
    public String refreshToken(RefreshTokenRequest refreshTokenRequest) {

        User user = userRepository.findByUserName(refreshTokenRequest.getUserName());
        return null;
    }
}
