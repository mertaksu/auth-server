package com.auth.server.config;

import com.auth.server.security.CustomAuthenticationManager;
import com.auth.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Order(1)
@RequiredArgsConstructor
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenStore jwtTokenStore;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final CustomAuthenticationManager customAuthenticationManager;

    private final UserService userService;

    private final DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        authorizationServerSecurityConfigurer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
        authorizationServerEndpointsConfigurer
                .tokenStore(jwtTokenStore)
                .tokenEnhancer(jwtAccessTokenConverter)
                .userDetailsService(userService)
                .authenticationManager(customAuthenticationManager);
    }
}
