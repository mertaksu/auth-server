package com.auth.server.entity;

import com.auth.server.pojo.AuthenticationProvider;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_GSM")
    private String userGsm;

    @Column(name = "USER_PASS")
    private String userPass;

    @Column(name = "IS_ACCOUNT_NON_EXPIRED",columnDefinition = "boolean default true")
    private boolean isAccountNonExpired=true;

    @Column(name = "IS_ACCOUNT_NON_LOCKED",columnDefinition = "boolean default true")
    private boolean isAccountNonLocked=true;

    @Column(name = "IS_CREDENTIALS_NON_EXPIRED",columnDefinition = "boolean default true")
    private boolean isCredentialsNonExpired=true;

    @Column(name = "IS_ENABLED",columnDefinition = "boolean default true")
    private boolean isEnabled=true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTH_PROVIDER")
    private AuthenticationProvider authenticationProvider = AuthenticationProvider.LOCAL;

    @Override
    public List<Privilege> getAuthorities() {
        List<Privilege> privileges = new ArrayList<>();
        roles.forEach(role -> privileges.addAll(role.getPrivileges()));
        return privileges;
    }

    @Override
    public String getPassword() {
        return userPass;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
