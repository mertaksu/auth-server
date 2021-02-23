package com.auth.server.repository;

import com.auth.server.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String userName);
}
