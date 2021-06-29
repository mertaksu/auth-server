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
            User user = userRepository.findByUserName("test");
        } else {
            Privilege readPrivilege
                    = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege
                    = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
            Privilege deletePrivilege
                    = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

            List<Privilege> adminPrivileges = Arrays.asList(
                    readPrivilege, writePrivilege, deletePrivilege);

            List<Privilege> updateablePrivilege = Arrays.asList(readPrivilege,writePrivilege);

            List<Privilege> deleteablePrivilege = Arrays.asList(readPrivilege,deletePrivilege);

            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER_READ_ONLY", Collections.singletonList(readPrivilege));
            createRoleIfNotFound("ROLE_USER_UPDATEABLE", updateablePrivilege);
            createRoleIfNotFound("ROLE_USER_DELETEABLE", deleteablePrivilege);

            UserRole adminRole = roleRepository.findByName("ROLE_ADMIN");
            User user = new User();
            user.setUserName("test");
            System.out.println("PasswordEncoder:"+passwordEncoder.hashCode());
            user.setUserPass(passwordEncoder.encode("test"));
            user.setUserEmail("test@test.com");
            user.setRoles(Collections.singletonList(adminRole));
            user.setUserGsm("905555555555");
            userRepository.save(user);

            UserRole readonlyRole = roleRepository.findByName("ROLE_USER_READ_ONLY");
            User user2 = new User();
            user2.setUserName("test2");
            System.out.println("PasswordEncoder:"+passwordEncoder.hashCode());
            user2.setUserPass(passwordEncoder.encode("test2"));
            user2.setUserEmail("test2@test.com");
            user2.setRoles(Collections.singletonList(readonlyRole));
            user2.setUserGsm("905555555555");
            userRepository.save(user2);

            UserRole updateableRole = roleRepository.findByName("ROLE_USER_UPDATEABLE");
            User user3 = new User();
            user3.setUserName("test3");
            System.out.println("PasswordEncoder:"+passwordEncoder.hashCode());
            user3.setUserPass(passwordEncoder.encode("test3"));
            user3.setUserEmail("test3@test.com");
            user3.setRoles(Collections.singletonList(updateableRole));
            user3.setUserGsm("905555555555");
            userRepository.save(user3);

            UserRole deleteableRole = roleRepository.findByName("ROLE_USER_DELETEABLE");
            User user4 = new User();
            user4.setUserName("test4");
            System.out.println("PasswordEncoder:"+passwordEncoder.hashCode());
            user4.setUserPass(passwordEncoder.encode("test4"));
            user4.setUserEmail("test4@test.com");
            user4.setRoles(Collections.singletonList(deleteableRole));
            user4.setUserGsm("905555555555");
            userRepository.save(user4);

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
            String name, List<Privilege> privileges) {

        UserRole role = roleRepository.findByName(name);
        if (role == null) {
            role = new UserRole();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
    }
}
