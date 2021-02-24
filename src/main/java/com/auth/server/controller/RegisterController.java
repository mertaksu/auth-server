package com.auth.server.controller;

import com.auth.server.exception.ConfirmedPasswordNotEqualsException;
import com.auth.server.exception.UserAlreadyExistException;
import com.auth.server.pojo.RegisterRequest;
import com.auth.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegisterController {

    private final RegisterService registerService;

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
}
