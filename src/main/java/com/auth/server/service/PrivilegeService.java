package com.auth.server.service;

import com.auth.server.entity.Privilege;
import com.auth.server.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PrivilegeService implements IPrivilegeService {
    @Override
    public List<String> getPrivileges(Collection<UserRole> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (UserRole role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getAuthority());
        }
        return privileges;
    }
}
