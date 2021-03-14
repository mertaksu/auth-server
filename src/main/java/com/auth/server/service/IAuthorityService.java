package com.auth.server.service;

import com.auth.server.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IAuthorityService {
    List<GrantedAuthority> getAuthorities(List<UserRole> roles);
}
