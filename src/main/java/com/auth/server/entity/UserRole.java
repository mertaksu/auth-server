package com.auth.server.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Table(name = "USER_ROLE")
@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Privilege> privileges;
}
