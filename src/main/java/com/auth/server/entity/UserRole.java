package com.auth.server.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "USER_ROLE")
@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Privilege> privileges;
}
