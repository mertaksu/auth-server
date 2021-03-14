package com.auth.server;

import com.auth.server.entity.Privilege;
import com.auth.server.entity.User;
import com.auth.server.entity.UserRole;
import com.auth.server.repository.PrivilegeRepository;
import com.auth.server.repository.RoleRepository;
import com.auth.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringAuthServerStarter implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthServerStarter.class,args);
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            Optional<User> user = userRepository.findByUserName("test");
            user.ifPresent(value -> System.out.println(value.getAuthorities()));
        } else {
            Privilege readPrivilege
                    = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege
                    = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

            List<Privilege> adminPrivileges = Arrays.asList(
                    readPrivilege, writePrivilege);
            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

            UserRole adminRole = roleRepository.findByName("ROLE_ADMIN");
            User user = new User();
            user.setUserName("test");
            System.out.println("PasswordEncoder:"+passwordEncoder.hashCode());
            user.setUserPass(passwordEncoder.encode("test"));
            user.setUserEmail("test@test.com");
            user.setRoles(Collections.singletonList(adminRole));
            user.setUserGsm("905555555555");
            userRepository.save(user);
            Optional<User> savedUser = userRepository.findByUserName("test");
            savedUser.ifPresent(value -> System.out.println(value.getAuthorities()));
        }

    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByAuthority(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setAuthority(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    void createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        UserRole role = roleRepository.findByName(name);
        if (role == null) {
            role = new UserRole();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
    }
}
