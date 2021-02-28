package com.auth.server.repository;

import com.auth.server.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole,Long> {
    UserRole findByName(String name);
}
