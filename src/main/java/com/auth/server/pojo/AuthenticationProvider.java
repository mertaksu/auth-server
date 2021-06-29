package com.auth.server.pojo;

import lombok.Getter;

@Getter
public enum AuthenticationProvider {
    LOCAL("local"), FACEBOOK("facebook");
    private String value;
    AuthenticationProvider(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
