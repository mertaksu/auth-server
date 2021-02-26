package com.auth.server.repository;

import com.auth.server.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUserName(String userName);
}
