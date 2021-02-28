package com.auth.server.repository;

import com.auth.server.entity.Privilege;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege,Long> {
    Privilege findByAuthority(String name);
}
