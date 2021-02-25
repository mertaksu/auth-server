package com.auth.server.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "USER_ROLE")
@Entity
@Data
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "PRIVILEGE",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    @Override
    public String getAuthority() {
        StringBuilder authority = new StringBuilder();
        privileges.stream().findAny().ifPresent(privilege -> authority.append(privilege.getName()));
        return authority.toString();
    }
}
