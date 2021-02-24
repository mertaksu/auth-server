package com.auth.server.controller;

import com.auth.server.pojo.LoginRequest;
import com.auth.server.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @RequestMapping(path = "/login" , method= RequestMethod.POST, consumes = "application/json" ,produces ="application/json")
    private ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        String token = null;
        try {
            token = loginService.login(loginRequest);
        } catch (IOException | URISyntaxException e) {
            log.error("Exception ",e);
        }
        if(token!=null) {
            httpServletResponse.setHeader("Token",token);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
