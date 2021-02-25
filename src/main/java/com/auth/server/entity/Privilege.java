package com.auth.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Table(name = "PRIVILEGE")
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<UserRole> roles;
}
