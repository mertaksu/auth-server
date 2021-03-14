package com.auth.server.service;

import com.auth.server.entity.Privilege;
import com.auth.server.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorityService implements IAuthorityService {

    private final IPrivilegeService privilegeService;

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public List<GrantedAuthority> getAuthorities(List<UserRole> roles) {
        return getGrantedAuthorities(privilegeService.getPrivileges(roles));
    }
}
