package com.auth.server.config;

import com.auth.server.security.CustomOauth2UserService;
import com.auth.server.security.OAuth2LoginSuccessHandler;
import com.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final CustomOauth2UserService oauth2UserService;

    private final PasswordEncoder passwordEncoder;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers("/login*","/signin/**","/signup/**","/auth/**", "/oauth2/**").permitAll()
            .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                .oauth2Login()
                .successHandler(oAuth2LoginSuccessHandler)
                .userInfoEndpoint()
                .userService(oauth2UserService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}