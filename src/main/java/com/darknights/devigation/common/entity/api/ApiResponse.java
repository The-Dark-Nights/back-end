package com.darknights.devigation.common.entity.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse {
    public int status;
    public String message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    public LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponse() {}

    public ApiResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
