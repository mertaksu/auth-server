package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    private final UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUserName(connection.getDisplayName());
        user.setUserPass(RandomStringUtils.randomAlphabetic(8));
        userRepository.save(user);
        return user.getUsername();
    }
}