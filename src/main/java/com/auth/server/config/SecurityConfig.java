package com.auth.server.config;

import com.auth.server.security.CustomAuthenticationManager;
import com.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Order(2)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(customAuthenticationManager).userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().formLogin()
                .and()
                .authorizeRequests().antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated();

        /*
        http.csrf().ignoringAntMatchers("/login");
        http.csrf().ignoringAntMatchers("/refresh");
        http.csrf().ignoringAntMatchers("/register");

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/login").permitAll().antMatchers("/refresh").permitAll()
                .antMatchers("/register").permitAll()
        ;
        */
    }
}