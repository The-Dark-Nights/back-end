package com.darknights.devigation.security.command.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthResponseBody {
    private String accessToken;

    public AuthResponseBody() {}

    public AuthResponseBody setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
