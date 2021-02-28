package com.auth.server.service;

import com.auth.server.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface IAuthorityService {
    Collection<? extends GrantedAuthority> getAuthorities(Collection<UserRole> roles);
}
