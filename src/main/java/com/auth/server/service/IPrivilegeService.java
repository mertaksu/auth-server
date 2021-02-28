package com.auth.server.service;

import com.auth.server.entity.UserRole;

import java.util.Collection;
import java.util.List;

public interface IPrivilegeService {
    List<String> getPrivileges(Collection<UserRole> roles);
}
