package com.auth.server.controller;

import com.auth.server.exception.ConfirmedPasswordNotEqualsException;
import com.auth.server.exception.UserAlreadyExistException;
import com.auth.server.pojo.RegisterRequest;
import com.auth.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegisterController {

    private final RegisterService registerService;

    int a;

    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;

    @Qualifier("tokenEnhancer")
    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    @RequestMapping(path = "/register",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            Boolean isRegistered = registerService.register(registerRequest);
            if(isRegistered)
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.badRequest().build();
        } catch (ConfirmedPasswordNotEqualsException | UserAlreadyExistException e) {
            log.error("Exception ",e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/oauth2/callback/facebook")
    public ResponseEntity<DefaultOAuth2AccessToken> facebookLogin(@RequestParam String code,@RequestParam String state) {

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(code);
        return ResponseEntity.ok().body(customAccessToken);
    }


    /*
     Custom Login controller

        @RequestMapping(path = "/login",method = RequestMethod.GET)
        public ResponseEntity<String> login() {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                "test",
                                "test2"
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok("OK");
            } catch (Exception e) {
                log.error("Error",e);
                return ResponseEntity.ok("FAIL");
            }
        }
     */
}
