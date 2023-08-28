package com.darknights.devigation.global.security.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshAccessTokenDTO {
    private String accessToken;

    public RefreshAccessTokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
