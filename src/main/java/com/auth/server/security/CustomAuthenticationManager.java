package com.auth.server.security;

import com.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        if(username!=null && password!=null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if(userDetails!=null && userDetails.getUsername().equals(username) && passwordEncoder.matches(password,userDetails.getPassword()))
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            throw new BadCredentialsException(username);
        } else {
            throw new InvalidParameterException("Username or password can not be null");
        }
    }
}
