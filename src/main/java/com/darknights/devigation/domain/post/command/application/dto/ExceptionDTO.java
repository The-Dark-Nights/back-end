package com.darknights.devigation.domain.post.command.application.dto;

import lombok.Getter;

@Getter
public class ExceptionDTO {
    private int status;
    private String reason;

    public ExceptionDTO(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }
}
