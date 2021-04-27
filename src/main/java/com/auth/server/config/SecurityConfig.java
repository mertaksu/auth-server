package com.auth.server.config;

import com.auth.server.service.FacebookConnectionSignup;
import com.auth.server.service.FacebookSignInAdapter;
import com.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final FacebookConnectionSignup facebookConnectionSignup;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().antMatchers("/login*","/signin/**","/signup/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(); // <-- this was changed


    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ConnectionFactoryLocator connectionFactoryLocator =
                connectionFactoryLocator();
        UsersConnectionRepository usersConnectionRepository =
                getUsersConnectionRepository(connectionFactoryLocator);
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository, new FacebookSignInAdapter());
    }

    private ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory("766821244270428", "d6646f127cfd2c6b161246637a644aad"));
        return registry;
    }

    private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
    }
}