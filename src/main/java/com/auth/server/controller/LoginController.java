package com.auth.server.controller;

import com.auth.server.pojo.LoginRequest;
import com.auth.server.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @RequestMapping(path = "/login" , method= RequestMethod.POST, consumes = "application/json" ,produces ="application/json")
    private ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = loginService.login(loginRequest);
        if(token!=null)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
