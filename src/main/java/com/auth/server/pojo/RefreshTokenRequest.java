package com.auth.server.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotNull
    @NotBlank
    private String userName;
    @NotNull
    @NotBlank
    private String token;
}
