package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.pojo.LoginRequest;
import com.auth.server.repository.UserRepository;
import com.auth.server.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public String login(LoginRequest loginRequest) throws IOException, URISyntaxException {
        String token;
        User user = (User) userService.loadUserByUsername(loginRequest.getUsername());
        if(user==null) {
            throw new UsernameNotFoundException("Username: "+loginRequest.getUsername()+" not found");
        } else {
            if(!passwordEncoder.matches(loginRequest.getPassword(),user.getUserPass())) {
                throw new BadCredentialsException("Invalid password for user: "+loginRequest.getUsername());
            } else {
                token = jwtService.tokenFor(user.getUsername(),"BASIC_ROLE");
            }
        }
        return token;
    }
}
