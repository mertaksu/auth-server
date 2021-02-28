package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.repository.RoleRepository;
import com.auth.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final IAuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        return user.map(value -> new org.springframework.security.core.userdetails.User(
                value.getUsername(), value.getPassword(), true, true, true, true,
                authorityService.getAuthorities(Collections.singletonList(roleRepository.findByName("ROLE_USER"))))).orElseGet(() -> new org.springframework.security.core.userdetails.User(
                " ", " ", true, true, true, true,
                authorityService.getAuthorities(Collections.singletonList(roleRepository.findByName("ROLE_USER")))));
    }
}
