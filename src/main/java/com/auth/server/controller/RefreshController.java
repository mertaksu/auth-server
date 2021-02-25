package com.auth.server.controller;

import com.auth.server.pojo.RefreshTokenRequest;
import com.auth.server.service.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class RefreshController {

    private final RefreshService refreshService;

    @RequestMapping(path = "/refresh",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest, HttpServletResponse httpServletResponse) {
       String token = refreshService.refreshToken(refreshTokenRequest);
       if(token != null) {
           httpServletResponse.setHeader("Token",token);
           return ResponseEntity.ok().build();
       } else
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
