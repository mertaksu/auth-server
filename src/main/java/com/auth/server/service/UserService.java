package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.entity.UserRole;
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

    private final IAuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByUserName(userName);

        return foundUser.map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isAccountNonExpired(), user.isAccountNonLocked(),
                authorityService.getAuthorities(getRolesOfUser(userName)))).orElseThrow(() -> new UsernameNotFoundException(userName));
    }

    private List<UserRole> getRolesOfUser(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if(user!=null && user.isPresent())
            return user.get().getRoles();
        else return null;
    }
}
