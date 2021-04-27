package com.auth.server.config;

import com.auth.server.security.CustomTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Base64;

@Configuration
public class JwtConfiguration {

    @Bean
    public JwtTokenStore jwtTokenStore(JwtAccessTokenConverter tokenEnhancer) {
        return new JwtTokenStore(tokenEnhancer);
    }

    @Bean
    public KeyPair keyPair() {
        return new KeyStoreKeyFactory(new ClassPathResource("astrolab.keystore"),"1234Aa".toCharArray()).getKeyPair("astrolab");
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        KeyPair keyPair = keyPair();
        converter.setKeyPair(keyPair);
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        return converter;
    }
}
